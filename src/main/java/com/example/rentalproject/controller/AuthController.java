package com.example.rentalproject.controller;

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
    public ResponseEntity<String> register(@RequestParam("login") String login, @RequestParam("password") String password,
                                           @RequestParam("name") String name, @RequestParam("surname") String surname){
        Long usr = userService.addUser(name, surname, login, password);
        if (usr == -1){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return createToken(usr);
    }


    @PostMapping(path = "login")
    public ResponseEntity<String> login(@RequestParam("login") String login, @RequestParam("password") String password){
        Long usr = authService.login(login, password);
        System.out.println(usr);
        if (usr == -1){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return createToken(usr);
    }


    @PostMapping(path = "createToken")
    public ResponseEntity<String> createToken(@RequestParam("userId")Long usr){
        String tkn = tokenService.createTokenForUser(usr);
        JSONObject json = new JSONObject();
        json.put("state", "ok");
        json.put("token", tkn);
        return ResponseEntity.ok(json.toString());
    }


    @PostMapping(path = "validToken")
    public ResponseEntity<String> validToken(@RequestParam("token") String token){
        Long usr = tokenService.validToken(token);
        if (usr == -1){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        tokenService.refresh(token);
        JSONObject json = new JSONObject();
        json.put("state", "ok");
        return ResponseEntity.ok(json.toString());
    }

}
