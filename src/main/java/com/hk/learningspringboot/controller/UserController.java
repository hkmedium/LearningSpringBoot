package com.hk.learningspringboot.controller;

import com.hk.learningspringboot.entity.User;
import com.hk.learningspringboot.response.ResponseHandler;
import com.hk.learningspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.hk.learningspringboot.utill.Constants.ENROLLED_SUCCESS_TEXT;
import static com.hk.learningspringboot.utill.Constants.REQUESTED_USER_TEXT;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/allUsersWithEnrolled")  //http://localhost:8089/api/user/allUsersWithEnrolled
    public ResponseEntity<Object> getAllUsersWithEnrolledCourses() {
        return ResponseHandler.responseBuilder(REQUESTED_USER_TEXT,
                HttpStatus.OK, userService.getAllUsersWithEnrolledCourses());
    }

    @GetMapping("/allUsers")  //http://localhost:8089/api/user/allUsers
    public ResponseEntity<Object> getAllUsers() {
        return ResponseHandler.responseBuilder(REQUESTED_USER_TEXT,
                HttpStatus.OK, userService.getAllUsers());
    }

    @GetMapping("/getUser/{userId}") //http://localhost:8089/api/user/getUser/1
    public ResponseEntity<Object> getUserById(@PathVariable("userId") int userId) {
        return ResponseHandler.responseBuilder(REQUESTED_USER_TEXT,
                HttpStatus.OK, userService.getUserById(userId));
    }

    @PutMapping("/updateUser")
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        return ResponseHandler.responseBuilder(userService.updateUser(user),
                HttpStatus.OK, null);
    }

    @DeleteMapping("/deleteUser/{userId}") //http://localhost:8089/api/user/deleteUser/1
    public ResponseEntity<Object> deleteUserById(@PathVariable("userId") int userId) {
        return ResponseHandler.responseBuilder(userService.deleteUser(userId),
                HttpStatus.OK, null);
    }

    @PutMapping("/{userId}/course/{courseId}") //http://localhost:8089/api/user/1/course/9
    public ResponseEntity<Object> addCourseToUser(
            @PathVariable int userId,
            @PathVariable int courseId
    ) {
        User savedUser = userService.addCourseToUser(userId, courseId);
        return ResponseHandler.responseBuilder(ENROLLED_SUCCESS_TEXT,
                HttpStatus.OK, savedUser);
    }

    @GetMapping("/{userId}/courses/{courseId}/enrolled") //http://localhost:8089/api/user/2/courses/3/enrolled
    public ResponseEntity<Object> checkUserEnrolled(@PathVariable int userId, @PathVariable int courseId) {
        boolean isEnrolled = userService.isUserEnrolledInCourse(userId, courseId);
        return ResponseHandler.responseBuilder("" + isEnrolled,
                HttpStatus.OK, null);
    }
}
