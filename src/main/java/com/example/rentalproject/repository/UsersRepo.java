package com.example.rentalproject.repository;


import com.example.rentalproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<User, Long> {
    public User findByLogin(String login);
    public Optional<User> findByLoginAndPassword(String login, String password);
}
