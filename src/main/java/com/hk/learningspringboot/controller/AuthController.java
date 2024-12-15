package com.hk.learningspringboot.controller;


import com.hk.learningspringboot.dto.AuthRequest;
import com.hk.learningspringboot.dto.RefreshTokenRequest;
import com.hk.learningspringboot.entity.User;
import com.hk.learningspringboot.response.ResponseHandler;
import com.hk.learningspringboot.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/createUser") //http://localhost:8089/api/createUser
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        return ResponseHandler.responseBuilder(authService.createUser(user),
                HttpStatus.CREATED, user);
    }
    @PostMapping("/login") //http://localhost:8089/api/login
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        System.out.println("AuthController login :: >> " + authRequest.toString());
        return authService.login(authRequest);
    }

    @PostMapping("/refresh-token") //http://localhost:8089/api/refresh-token
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest.getRefreshToken());
    }

    @GetMapping("/login/testprint") //http://localhost:8089/api/login/testprint
    public ResponseEntity<Object> testRandomNumber() {
        return ResponseHandler.responseBuilder("Random number is generated withing 5",
                HttpStatus.OK, "true or false based on random number: " + authService.testRandomNumber());
    }
}

