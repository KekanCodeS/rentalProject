package com.example.rentalproject.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegiserRequest {
    String login;
    String password;
    String name;
    String surname;
}
