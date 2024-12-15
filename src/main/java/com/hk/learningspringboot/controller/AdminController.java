package com.hk.learningspringboot.controller;

import com.hk.learningspringboot.entity.User;
import com.hk.learningspringboot.response.ResponseHandler;
import com.hk.learningspringboot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 When a user is authenticated and has the ADMIN role,
 they can access any route under /admin/**.
 This is typically checked after authentication,
 and a JWT token is passed in the request to validate the user's credentials and roles.
 Log in as an Admin: First, make a request to your login endpoint (like /login) and receive a JWT token.
 This token will contain the role (e.g., "ADMIN") in its claims.
 Make Admin Requests: Attach the token in the Authorization header as a Bearer token
 when making requests to /admin/**.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/testAdmin")
    public ResponseEntity<Object> testAdminRoll() {
        return ResponseHandler.responseBuilder("Admin roll tested",
                HttpStatus.OK, adminService.testAdminRoll());
    }

    @GetMapping("/dashboard")
    public ResponseEntity<String> adminDashboard() {
        return ResponseEntity.ok("Welcome to the Admin Dashboard!");
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        // Logic to fetch all users, only accessible to admin
        return ResponseEntity.ok(adminService.getAllUsers());
    }
}
