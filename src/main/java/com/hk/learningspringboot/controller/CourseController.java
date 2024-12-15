package com.hk.learningspringboot.controller;

import com.hk.learningspringboot.dto.CourseDTO;
import com.hk.learningspringboot.dto.UserDTO;
import com.hk.learningspringboot.entity.Course;
import com.hk.learningspringboot.entity.CourseHitcount;
import com.hk.learningspringboot.response.ResponseHandler;
import com.hk.learningspringboot.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hk.learningspringboot.utill.Constants.*;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/courses/{categoryId}") //http://localhost:8089/api/course/createCourse/1
    public ResponseEntity<Object> createCourse(@RequestBody Course course, @PathVariable("categoryId") int categoryId) {
        return ResponseHandler.responseBuilder(courseService.createCourse(course, categoryId),
                HttpStatus.CREATED, null);
    }

    @GetMapping("/courses") //http://localhost:8089/api/course/allCourses
    public ResponseEntity<Object> getAllCourses() {
        return ResponseHandler.responseBuilder(REQUESTED_COURSE_TEXT,
                HttpStatus.OK, courseService.getAllCourses());
    }

    @GetMapping("/courses/{courseId}") //http://localhost:8089/api/course/courses?courseId=2
    public ResponseEntity<Object> getCourseById(@RequestParam int courseId) {
        return ResponseHandler.responseBuilder(REQUESTED_COURSE_TEXT,
                HttpStatus.OK, courseService.getCourseById(courseId));
    }

    @GetMapping("/getCoursesByCategoryId") //http://localhost:8089/api/course/getCoursesByCategoryId?categoryId=2
    public ResponseEntity<Object> getCoursesByCategoryID(@RequestParam int categoryId) {
        return ResponseHandler.responseBuilder(REQUESTED_COURSE_TEXT,
                HttpStatus.OK, courseService.getCoursesByCategoryId(categoryId));
    }

    @GetMapping("/getCoursesDetailsByCategoryId") //http://localhost:8089/api/course/getCoursesDetailsByCategoryId?categoryId=2
    public ResponseEntity<Object> getCoursesByCategoryIDRawQuery(@RequestParam int categoryId) {
        return ResponseHandler.responseBuilder(REQUESTED_COURSE_TEXT,
                HttpStatus.OK, courseService.getCoursesByCategoryIdRawQuery(categoryId));
    }

    @GetMapping("/{courseId}/users") //http://localhost:8089/api/course/2/users
    public ResponseEntity<Object> getUsersByCourse(@PathVariable int courseId) {
        List<UserDTO> users = courseService.getUsersByCourseId(courseId);
        return ResponseHandler.responseBuilder(USER_TEXT,
                HttpStatus.OK, users);
    }

    @PutMapping("/updateCourse")
    public ResponseEntity<Object> updateCourse(@RequestBody Course course) {
        return ResponseHandler.responseBuilder(courseService.updateCourse(course),
                HttpStatus.OK, null);
    }

    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<Object> deleteCourseById(@PathVariable("courseId") int courseId) {
        return ResponseHandler.responseBuilder(courseService.deleteCourse(courseId),
                HttpStatus.OK, null);
    }

    @PutMapping("/{courseId}/hit") //http://localhost:8089/api/course/2/hit
    public ResponseEntity<CourseHitcount> updateCourseHitscount(@PathVariable int courseId) {
        return new ResponseEntity<>(courseService.updateCourseHitsCount(courseId), HttpStatus.OK);
    }


    @GetMapping("/{courseId}/gethitcount") //http://localhost:8089/api/course/2/gethitcount
    public ResponseEntity<Integer> getCourseHitCount(@PathVariable int courseId) {
        int hitCount = courseService.getCourseHitCount(courseId);
        return ResponseEntity.ok(hitCount);
    }

    @GetMapping("/{courseId}") //http://localhost:8089/api/course/2
    public ResponseEntity<Object> getCourseDetails(@PathVariable int courseId) {
        CourseDTO course = courseService.getCourseDetails(courseId);
        return ResponseHandler.responseBuilder(COURSE_TEXT,
                HttpStatus.OK, course);
    }
}
