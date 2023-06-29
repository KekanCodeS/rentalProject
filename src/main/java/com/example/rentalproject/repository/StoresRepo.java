package com.example.rentalproject.repository;


import com.example.rentalproject.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoresRepo extends JpaRepository<Store, Long> {
}
