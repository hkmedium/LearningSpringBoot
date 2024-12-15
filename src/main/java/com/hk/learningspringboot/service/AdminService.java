package com.hk.learningspringboot.service;

import com.hk.learningspringboot.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.hk.learningspringboot.utill.Constants.SUCCESS_TEXT;

@Service
public class AdminService {

    public String testAdminRoll() {
        Random rand = new Random();
        int number = rand.nextInt(20);
        return SUCCESS_TEXT + " Random number: " + number;
    }

    public List<User> getAllUsers() {

        return new ArrayList<>();
    }
}
