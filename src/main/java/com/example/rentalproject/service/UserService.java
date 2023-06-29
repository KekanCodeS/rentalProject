package com.example.rentalproject.service;

import com.example.rentalproject.entity.User;
import com.example.rentalproject.enums.Role;
import com.example.rentalproject.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UsersRepo usersRepo;

    public List<User> getAll(){
        return usersRepo.findAll();
    }

    public void addUser(){
        User res = new User();
        usersRepo.save(res);
    }

    public Long addUser(String name, String surname, String login, String psw){
        if (usersRepo.findByLogin(login) != null)
            return -1L;
        User res = new User();
        res.setName(name);
        res.setSurname(surname);
        res.setLogin(login);
        res.setPassword(psw);
        res.setRole(Role.COMMON);
        usersRepo.save(res);
        return usersRepo.findByLogin(login).getId();
    }
}
