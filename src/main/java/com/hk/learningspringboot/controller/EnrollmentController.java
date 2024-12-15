//package com.hk.learningspringboot.controller;
//
//
//import com.hk.learningspringboot.entity.Enrollment;
//import com.hk.learningspringboot.response.ResponseHandler;
//import com.hk.learningspringboot.service.EnrollmentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/enrollment")
//public class EnrollmentController {
//    @Autowired
//    private EnrollmentService enrollmentService;
//
//    @PutMapping("/enroll") //http://localhost:8089/enrollment/enroll?userId=1&courseId=101
//    public ResponseEntity<Object> enroll(@RequestParam int userId, @RequestParam int courseId) {
//        return ResponseHandler.responseBuilder(enrollmentService.enrollUserInCourse(userId, courseId), HttpStatus.OK, null);
//    }
//
//    @GetMapping("/user/{userId}/course/{courseId}")
//    public ResponseEntity<Enrollment> getEnrollment(@PathVariable int userId, @PathVariable int courseId) {
//
//        Optional<Enrollment> enrollment = enrollmentService.getEnrollment(userId, courseId);
//        return enrollment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("/user/{userId}/course/{courseId}/enrolled")//http://localhost:8089/enrollment/user/1/course/101/enrolled
//    public ResponseEntity<Boolean> checkEnrollment(
//            @PathVariable int userId,
//            @PathVariable int courseId) {
//
//        boolean isEnrolled = enrollmentService.isUserEnrolled(userId, courseId);
//        return ResponseEntity.ok(isEnrolled);
//    }
//}
