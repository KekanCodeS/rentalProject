package com.example.rentalproject.service;

import com.example.rentalproject.entity.Category;
import com.example.rentalproject.entity.Product;
import com.example.rentalproject.repository.CategoriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoriesRepo categoriesRepo;

    public void addCategory(String name){
        Category res = new Category();
        res.setName(name);
        categoriesRepo.save(res);
    }

    public List<Category> getAll(){
        return categoriesRepo.findAll();
    }

    public void setNameForCategory(Long id, String name){
        Category res = categoriesRepo.findById(id).get();
        res.setName(name);
        categoriesRepo.save(res);
    }

}
