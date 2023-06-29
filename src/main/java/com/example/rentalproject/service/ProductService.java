package com.example.rentalproject.service;

import com.example.rentalproject.entity.Product;
import com.example.rentalproject.entity.ProductAmount;
import com.example.rentalproject.repository.CategoryRepo;
import com.example.rentalproject.repository.ProductsAmountRepo;
import com.example.rentalproject.repository.ProductsRepo;
import com.example.rentalproject.repository.StoresRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductsRepo productsRepo;
    @Autowired
    ProductsAmountRepo productsAmountRepo;
    @Autowired
    StoresRepo storesRepo;
    @Autowired
    CategoryRepo categoryRepo;

    public void setNameForProduct(Long id, String name){
        Product res = productsRepo.findById(id).get();
        res.setName(name);
        productsRepo.save(res);
    }

    public List<Product> getProductsByCategoryId(Long id){
        return productsRepo.findAllByCategoryId(id);
    }

    public List<Product> getAllProductsByStoreId(Long id){
        List<Product> res = new ArrayList<>();
        for (ProductAmount pa : productsAmountRepo.findByStore(storesRepo.findById(id).get())) {
            res.add(pa.getProduct());
        }
        return res;
    }

    public void setCategoryForProduct(Long id, Long cat){
        Product res = productsRepo.findById(id).get();
        res.setCategory(categoryRepo.findById(cat).get());
        productsRepo.save(res);
    }

    public void addProduct(String name, String desc, Float pledge, Float price, Long cat, String specs){
        Product res = new Product();
        res.setName(name);
        res.setDescription(desc);
        res.setPledge(pledge);
        res.setPrice(price);
        res.setCategory(categoryRepo.findById(cat).get());
        res.setSpecs(specs);
        productsRepo.save(res);
    }

    public List<Product> getAll(){
        return productsRepo.findAll();
    }

    public List<Product> getInStock(){
        List<ProductAmount> lst = productsAmountRepo.findAll();
        List<Product> res = new ArrayList<>();
        for (ProductAmount pr:lst) {
            if (pr.getAmount() > 0 && !res.contains(pr.getProduct())) res.add(pr.getProduct());
        }
        return res;
    }

    public List<Product> getInStockByCategory(Long id){
        List<Product> lst = getInStock();
        return lst.stream().filter(x -> x.getCategory().getId().equals(id)).toList();
    }

    public Product getProduct(Long id){
        return productsRepo.findById(id).get();
    }

}
