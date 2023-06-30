package com.example.rentalproject.controller;

import com.example.rentalproject.entity.User;
import com.example.rentalproject.pojo.AuthRequest;
import com.example.rentalproject.pojo.RegiserRequest;
import com.example.rentalproject.service.AuthService;
import com.example.rentalproject.service.TokenService;
import com.example.rentalproject.service.UserService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;


    @PostMapping(path = "register")
    public ResponseEntity<String> register(@RequestBody RegiserRequest reg){
        Long usr = userService.addUser(reg.getName(), reg.getSurname(), reg.getLogin(), reg.getPassword());
        if (usr == -1){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return createToken(usr);
    }


    @PostMapping(path = "login")
    public ResponseEntity<String> login(@RequestBody AuthRequest auth){
        Long usr = authService.login(auth.getLogin(),auth.getPassword());
        if (usr == -1){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return createToken(usr);
    }


    @PostMapping(path = "createToken")
    public ResponseEntity<String> createToken(@RequestParam("userId")Long usr){
        String tkn = tokenService.createTokenForUser(usr);
        JSONObject json = new JSONObject();
        json.put("token", tkn);
        return ResponseEntity.ok(json.toString());
    }


    @PostMapping(path = "validToken")
    public ResponseEntity<String> validToken(@RequestParam("token") String token){
        Long usr = tokenService.validToken(token);
        if (usr == -1){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        tokenService.refresh(token);
        return ResponseEntity.ok().build();
    }


    @GetMapping(path = "userByToken")
    public ResponseEntity<User> getUserByToken(@RequestParam("token") String token){
        Long usr = tokenService.validToken(token);
        if (usr == -1){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok().body(userService.getById(usr).get());
    }

    @GetMapping(path = "isManager")
    public ResponseEntity<String> checkForManager(@RequestParam("token") String token){
        Long usr = tokenService.validToken(token);
        if (usr != -1 && authService.isManager(usr) != -1){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping(path = "isAdmin")
    public ResponseEntity<String> checkForAdmin(@RequestParam("token") String token){
        Long usr = tokenService.validToken(token);
        if (usr != -1 && authService.isAdmin(usr) != -1){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
