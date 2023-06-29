package com.example.rentalproject.controller;

import com.example.rentalproject.entity.Purchase;
import com.example.rentalproject.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;

    @GetMapping(path = "purchasesForUser")
    public ResponseEntity<List<Purchase>> getPurchasesForUser(@RequestParam Long user){
        return ResponseEntity.ok().body(purchaseService.getPurchasesForUser(user));
    }

    @GetMapping(path = "purchasesForStore")
    public ResponseEntity<List<Purchase>> getActivePurchasesForStore(@RequestParam Long store){
        return ResponseEntity.ok().body(purchaseService.getActivePurchasesForStore(store));
    }

}
