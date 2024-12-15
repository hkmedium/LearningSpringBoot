package com.hk.learningspringboot.service;

import com.hk.learningspringboot.dto.AuthRequest;
import com.hk.learningspringboot.dto.AuthResponse;
import com.hk.learningspringboot.entity.User;
import com.hk.learningspringboot.exception.ResourceAlreadyExistsException;
import com.hk.learningspringboot.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

import static com.hk.learningspringboot.utill.Constants.*;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private IUserRepo userRepository;
//    @Autowired
//    private UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthenticationManager authenticationManager, JwtTokenService jwtTokenService, IUserRepo userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String createUser(User user) {
        try {
            // check if user already exist. if exist than authenticate the user
            if(userRepository.findByUsername(user.getUsername()).isPresent()) {
                return "User already exist";
            }

            System.out.println("AuthService createUser :: >> getPassword: " + user.getPassword());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            System.out.println("AuthService createUser :: >> getPassword: " + user.getPassword());
            return SUCCESS_TEXT;
        } catch (DataIntegrityViolationException e) {
            throw new ResourceAlreadyExistsException(USER_TEXT);
        } catch (Exception e) {
            throw new RuntimeException(INTERNAL_ERROR_TEXT);
        }
    }

    public ResponseEntity<?> login(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();

        String accessToken = jwtTokenService.generateAccessToken(user);
        String refreshToken = jwtTokenService.generateRefreshToken(user);

        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    }

    public ResponseEntity<?> refreshToken(String refreshToken) {
        String username = jwtTokenService.getUsernameFromToken(refreshToken);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("No user found"));

        if (jwtTokenService.isValidRefreshToken(refreshToken, user.getUsername())) {
            String newAccessToken = jwtTokenService.generateAccessToken(user);
            return ResponseEntity.ok(new AuthResponse(newAccessToken, refreshToken));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    public boolean testRandomNumber() {
        int randomNum = new Random().nextInt(5);
        boolean status = false;
        if (randomNum == 2 || randomNum == 4) {
            status = true;
        }
        return status;
    }
}

