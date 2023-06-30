package com.example.rentalproject.controller;

import com.example.rentalproject.entity.Purchase;
import com.example.rentalproject.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = "getPurchase")
    public ResponseEntity<Purchase> getPurchase(@RequestParam Long id){
        Optional<Purchase> pr = purchaseService.getById(id);
        if (pr.isPresent())
            return ResponseEntity.ok().body(pr.get());
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

}
