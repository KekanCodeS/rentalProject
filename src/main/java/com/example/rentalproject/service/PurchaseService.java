package com.example.rentalproject.service;

import com.example.rentalproject.entity.Product;
import com.example.rentalproject.entity.Purchase;
import com.example.rentalproject.entity.Store;
import com.example.rentalproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public int addPurchase(Long userId, Long productId, Long storeId){
        Purchase res = new Purchase();
        Product prod = productsRepo.findById(productId).get();
        Store store = storesRepo.findById(storeId).get();
        if (productAmountService.getProductAmountByStore(productId, storeId) < 0) return -1;
        res.setUser(usersRepo.findById(userId).get());
        res.setProduct(prod);
        res.setStartPrice(prod.getPrice());
        res.setStartPledge(prod.getPledge());
        purchasesRepo.save(res);
        return 1;
    }


}
