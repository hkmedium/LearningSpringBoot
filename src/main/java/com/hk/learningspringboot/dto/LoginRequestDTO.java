package com.hk.learningspringboot.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    private String userEmail;
    private String userPassword;
}
