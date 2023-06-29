package com.example.rentalproject.service;

import com.example.rentalproject.entity.Token;
import com.example.rentalproject.entity.User;
import com.example.rentalproject.repository.TokensRepo;
import com.example.rentalproject.repository.UsersRepo;
import com.example.rentalproject.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TokenService {
    @Autowired
    TokensRepo tokensRepo;
    @Autowired
    UsersRepo usersRepo;

    public Long validToken(String token){
        Token tkn = tokensRepo.findByToken(token);
        if (tkn != null){
            LocalDateTime curDate = LocalDateTime.now();
            if (curDate.isBefore(tkn.getDate()))
                return tkn.getUser().getId();
            tokensRepo.delete(tkn);
        }
        return -1L;
    }

    public void refresh(String token){
        Token tkn = tokensRepo.findByToken(token);
        tkn.setDate(LocalDateTime.now().plusDays(1));
        tokensRepo.save(tkn);
    }

    public String createTokenForUser(Long id){
        if (usersRepo.findById(id).isEmpty())
            return "";
        Token res = new Token();
        User usr = usersRepo.findById(id).get();
        String tkn = StringUtil.generateStringWithAlphabet("qwertyuopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789", 64);
            while (tokensRepo.findByToken(tkn) != null){
                tkn = StringUtil.generateStringWithAlphabet("qwertyuopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789", 64);
            }
        LocalDateTime spoil = LocalDateTime.now().plusDays(1);
        res.setToken(tkn);
        res.setUser(usr);
        res.setDate(spoil);
        System.out.println(res);
        tokensRepo.save(res);
        return tkn;
    }

}
