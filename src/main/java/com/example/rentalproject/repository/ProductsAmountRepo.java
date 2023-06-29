package com.example.rentalproject.repository;

import com.example.rentalproject.entity.ProductAmount;
import com.example.rentalproject.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsAmountRepo extends JpaRepository<ProductAmount, Long> {
    public List<ProductAmount> findByStore(Store store);
    public ProductAmount findByProductIdAndStoreId(Long prod, Long store);
}
