package com.example.rentalproject.service;

import com.example.rentalproject.entity.Product;
import com.example.rentalproject.entity.ProductAmount;
import com.example.rentalproject.entity.Purchase;
import com.example.rentalproject.entity.Store;
import com.example.rentalproject.enums.PledgeStatus;
import com.example.rentalproject.enums.PurchaseStatus;
import com.example.rentalproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    @Autowired
    private PurchasesRepo purchasesRepo;
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private ProductsRepo productsRepo;
    @Autowired
    private StoresRepo storesRepo;
    @Autowired
    private ProductAmountService productAmountService;


    public List<Purchase> getAll(){
        return purchasesRepo.findAll();
    }

    public Long addPurchase(Long userId, Long productId, Long storeId){
        Purchase res = new Purchase();
        ProductAmount amount = productAmountService.getProductAmountByProductAndStore(productId, storeId);
        if (productAmountService.getProductAmountByStore(productId, storeId) < 1
                || usersRepo.findById(userId).isEmpty()
                || storesRepo.findById(storeId).isEmpty())
            return -1L;
        res.setUser(usersRepo.findById(userId).get());
        Product prod = productsRepo.findById(productId).get();
        Store store = storesRepo.findById(storeId).get();
        res.setProduct(prod);
        res.setStartPrice(prod.getPrice());
        res.setStartPledge(prod.getPledge());
        res.setStore(store);
        res.setStatus(PurchaseStatus.ORDERED);
        purchasesRepo.save(res);
        amount.setAmount(amount.getAmount()-1);
        return 1L;
    }

    public List<Purchase> getPurchasesForUser(Long usr){
        return purchasesRepo.getPurchasesByUserId(usr);
    }

    public List<Purchase> getActivePurchasesForStore(Long store){
        return purchasesRepo.getPurchasesByStoreId(store).stream().
                filter(x -> x.getStatus() != PurchaseStatus.ENDED).toList();
    }

    public Long setStatus(Long id, PurchaseStatus status){
        Optional<Purchase> pch = purchasesRepo.findById(id);
        if(pch.isEmpty())
            return -1L;
        Purchase purchase = pch.get();
        purchase.setStatus(status);
        purchasesRepo.save(purchase);
        return 1L;
    }

    public Long setPledgeStatus(Long id, PledgeStatus pledgeStatus){
        Optional<Purchase> pch = purchasesRepo.findById(id);
        if(pch.isEmpty())
            return -1L;
        Purchase purchase = pch.get();
        purchase.setPledgeStatus(pledgeStatus);
        purchasesRepo.save(purchase);
        return 1L;
    }

    public Long startRent(Long id){
        Optional<Purchase> prs = purchasesRepo.findById(id);
        if (!prs.isEmpty() && prs.get().getPledgeStatus() == PledgeStatus.PAID){
            Purchase purchase = prs.get();
            purchase.setRentStart(LocalDateTime.now());
            purchase.setStatus(PurchaseStatus.STARTED);
            purchasesRepo.save(purchase);
            return 1L;
        }
        return -1L;
    }

    public Long stopRent(Long id){
        Optional<Purchase> prs = purchasesRepo.findById(id);
        if (!prs.isEmpty() && prs.get().getStatus() == PurchaseStatus.STARTED){
            Purchase purchase = prs.get();
            purchase.setRentEnd(LocalDateTime.now());
            purchase.setStatus(PurchaseStatus.STOPPED);
            purchase.setTotalPrice(purchase.getStartPrice() *
                    ((float)Duration.between(purchase.getRentStart(), purchase.getRentEnd())
                            .toMinutes())/60);
            purchasesRepo.save(purchase);
            return 1L;
        }
        return -1L;
    }

}
