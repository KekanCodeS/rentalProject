package com.example.rentalproject.service;

import com.example.rentalproject.entity.User;
import com.example.rentalproject.enums.Role;
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

    public Long isManager(Long id){
        Optional<User> usr = usersRepo.findById(id);
        if (usr.isPresent() && usr.get().getRole().equals(Role.MANAGER)) return 1L;
        return -1L;
    }

    public Long isAdmin(Long id){
        Optional<User> usr = usersRepo.findById(id);
        if (usr.isPresent() && usr.get().getRole().equals(Role.ADMIN)) return 1L;
        return -1L;
    }

}
