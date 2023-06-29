package com.example.rentalproject.controller;

import com.example.rentalproject.entity.Category;
import com.example.rentalproject.entity.Product;
import com.example.rentalproject.service.CategoryService;
import com.example.rentalproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;


    @CrossOrigin
    @GetMapping(path ="category")
    public ResponseEntity<List<Product>> productsInStockByCategory(@RequestParam Long id){
        return ResponseEntity.ok().body(productService.getProductsByCategoryId(id));
    }

    @CrossOrigin
    @GetMapping(path = "categories")
    public ResponseEntity<List<Category>> getCategories(){
        return ResponseEntity.ok().body(categoryService.getAll());
    }

    @CrossOrigin
    @GetMapping(path = "product")
    public ResponseEntity<Product> getProduct(@RequestParam Long id){
        return ResponseEntity.ok().body(productService.getProduct(id));
    }


}
