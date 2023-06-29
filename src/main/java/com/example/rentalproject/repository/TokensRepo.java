package com.example.rentalproject.repository;


import com.example.rentalproject.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokensRepo extends JpaRepository<Token, Long> {
    public Token findByToken(String token);
    public Optional<Token> findByUserId(Long id);

}
