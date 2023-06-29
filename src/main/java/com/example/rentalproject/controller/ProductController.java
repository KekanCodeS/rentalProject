package com.example.rentalproject.controller;

import com.example.rentalproject.entity.Category;
import com.example.rentalproject.entity.Product;
import com.example.rentalproject.entity.Store;
import com.example.rentalproject.service.CategoryService;
import com.example.rentalproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;


    @GetMapping(path ="category")
    public ResponseEntity<List<Product>> productsInStockByCategory(@RequestParam Long id){
        return ResponseEntity.ok().body(productService.getProductsByCategoryId(id));
    }


    @GetMapping(path = "categories")
    public ResponseEntity<List<Category>> getCategories(){
        return ResponseEntity.ok().body(categoryService.getAll());
    }


    @GetMapping(path = "product")
    public ResponseEntity<Product> getProduct(@RequestParam Long id){
        Optional<Product> pr = productService.getProduct(id);
        if (pr.isEmpty()) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        return ResponseEntity.ok().body(pr.get());
    }

    @GetMapping(path = "stores")
    public ResponseEntity<List<Store>> getStoresForProduct(@RequestParam Long id){
        return ResponseEntity.ok().body(productService.getSoresByProduct(id));
    }

}
