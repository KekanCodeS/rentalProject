package com.example.rentalproject.repository.controller;

import com.example.rentalproject.entity.Purchase;
import com.example.rentalproject.service.PurchaseService;
import com.example.rentalproject.service.TokenService;
import com.example.rentalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController("/rent")
public class RentController {
    @Autowired
    PurchaseService purchaseService;
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    @PostMapping(path = "create")
    public ResponseEntity<String> createPurchase(@RequestParam Long user, @RequestParam Long product,
                                                 @RequestParam Long store, @RequestParam String token){
        if (tokenService.validToken(token) != -1L)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        if (purchaseService.addPurchase(user, product, store) == -1)
            return  ResponseEntity.status(HttpStatus.CONFLICT).build();
        return ResponseEntity.ok().build();
    }

//    @PostMapping(path = "")
//    public ResponseEntity<> startRent(){
//
//    }
//
//
//    @GetMapping(path = "getPurchases")
//    public List<Purchase> getPurchases(Long user){
//        return null;
//    }

}
