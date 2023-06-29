package com.example.rentalproject.repository;

import com.example.rentalproject.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchasesRepo extends JpaRepository<Purchase, Long> {
    public List<Purchase> getPurchasesByUserId(Long id);
    public List<Purchase> getPurchasesByStoreId(Long id);

}
