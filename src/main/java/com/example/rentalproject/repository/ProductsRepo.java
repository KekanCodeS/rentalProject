package com.example.rentalproject.repository;

import com.example.rentalproject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepo extends JpaRepository<Product, Long> {

    public List<Product> findAllByCategoryId(Long id);
}
