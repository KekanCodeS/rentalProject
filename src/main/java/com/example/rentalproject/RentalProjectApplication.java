package com.example.rentalproject;

import com.example.rentalproject.repository.TokensRepo;
import com.example.rentalproject.repository.UsersRepo;
import com.example.rentalproject.service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RentalProjectApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(RentalProjectApplication.class, args);

        CategoryService cs = ctx.getBean(CategoryService.class);
        ProductService ps = ctx.getBean(ProductService.class);
        UserService us = ctx.getBean(UserService.class);
        UsersRepo ur = ctx.getBean(UsersRepo.class);
        TokensRepo tr = ctx.getBean(TokensRepo.class);
        AuthService as = ctx.getBean(AuthService.class);
        TokenService ts = ctx.getBean(TokenService.class);


        System.out.println(cs.getAll());



    }

}
