package com.example.rentalproject.repository;

import com.example.rentalproject.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasesRepo extends JpaRepository<Purchase, Long> {

}
