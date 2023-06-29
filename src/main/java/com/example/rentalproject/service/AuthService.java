package com.example.rentalproject.service;

import com.example.rentalproject.entity.User;
import com.example.rentalproject.repository.TokensRepo;
import com.example.rentalproject.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private TokenService tokenService;

    public Long login(String login, String psw){
        Optional<User> usr = usersRepo.findByLoginAndPassword(login, psw);
        if(usr.isPresent())
            return usr.get().getId();
        return -1L;
    }

}
