package com.example.rentalproject.service;

import com.example.rentalproject.entity.ProductAmount;
import com.example.rentalproject.repository.ProductsAmountRepo;
import com.example.rentalproject.repository.StoresRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductAmountService {
    @Autowired
    ProductsAmountRepo productsAmountRepo;
    @Autowired
    StoresRepo storesRepo;

    public List<ProductAmount> getAll(){
        return productsAmountRepo.findAll();
    }

    public Long getProductAmountByStore(Long prodId, Long storeId){
        return  productsAmountRepo.findByProductIdAndStoreId(prodId, storeId).getAmount();
    };

    public ProductAmount getProductAmountByProductAndStore(Long prod, Long store){
        return productsAmountRepo.findByProductIdAndStoreId(prod, store);
    };
}
