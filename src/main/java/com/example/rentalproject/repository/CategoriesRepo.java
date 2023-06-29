package com.example.rentalproject.repository;

import com.example.rentalproject.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepo extends JpaRepository<Category,Long> {
}
