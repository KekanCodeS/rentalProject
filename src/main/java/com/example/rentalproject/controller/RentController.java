package com.example.rentalproject.controller;

import com.example.rentalproject.entity.Purchase;
import com.example.rentalproject.enums.PledgeStatus;
import com.example.rentalproject.service.PurchaseService;
import com.example.rentalproject.service.TokenService;
import com.example.rentalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/rent")
public class RentController {
    @Autowired
    PurchaseService purchaseService;
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;


    @PostMapping(path = "create")
    public ResponseEntity<String> createPurchase(@RequestParam Long product,
                                                 @RequestParam Long store, @RequestParam String token){
        Long usr = tokenService.validToken(token);
        if (usr == -1L)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        if (purchaseService.addPurchase(usr, product, store) == -1)
            return  ResponseEntity.status(HttpStatus.CONFLICT).build();
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "pledge")
    public ResponseEntity<String> payPledge(@RequestParam Long id, @RequestParam String token){
        Long usr = tokenService.validToken(token);
        Optional<Purchase> prc = purchaseService.getById(id);
        if (usr != -1 && prc.isPresent()){
            Purchase purchase = prc.get();
            if (purchase.getUser().getId().equals(usr)){
                purchaseService.setPledgeStatus(id, PledgeStatus.PAID);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


    @PostMapping(path = "startRent")
    public ResponseEntity<String> startRent(@RequestParam Long id,@RequestParam String token){
        Long usr = tokenService.validToken(token);
        if (usr != -1 && userService.isManager(usr) != -1){
            purchaseService.startRent(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping(path = "stopRent")
    public ResponseEntity<String> stopRent(@RequestParam Long id,@RequestParam String token){
        Long usr = tokenService.validToken(token);
        if (usr != -1 && userService.isManager(usr) != -1){
            purchaseService.stopRent(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping(path = "endRent")
    public ResponseEntity<String> endRent(@RequestParam Long id, @RequestParam String token){
        Long usr = tokenService.validToken(token);
        if (usr != -1 && userService.isManager(usr) != -1){
            purchaseService.endRent(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

}
