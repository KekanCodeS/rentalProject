package com.example.rentalproject.service;

import com.example.rentalproject.entity.Store;
import com.example.rentalproject.repository.StoresRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {
    @Autowired
    StoresRepo storesRepo;

    public List<Store> getAll(){
        return storesRepo.findAll();
    }

    public void setStore(Long id, String name, String address){
        Store res = storesRepo.findById(id).get();
        res.setName(name);
        res.setAddress(address);
        storesRepo.save(res);
    }

    public void addStore(String name, String address){
        Store res = new Store();
        res.setName(name);
        res.setAddress(address);
        storesRepo.save(res);
    }

}
