package com.hk.learningspringboot.controller;

import com.hk.learningspringboot.dto.CourseRatingResponseDTO;
import com.hk.learningspringboot.entity.CourseRating;
import com.hk.learningspringboot.response.ResponseHandler;
import com.hk.learningspringboot.service.CourseRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.hk.learningspringboot.utill.Constants.REQUESTED_USER_TEXT;

@RestController
@RequestMapping("/api/ratings")
public class CourseRatingController {
    @Autowired
    private CourseRatingService courseRatingService;
    // Create or update a course rating
    @PostMapping("/saveCourseRating") //http://localhost:8089/api/ratings/saveCourseRating
    public ResponseEntity<Object> saveCourseRating(@RequestBody CourseRating courseRating) {
        if (courseRatingService.existsByCourseAndUser(courseRating.getCourse().getCourseId(), courseRating.getUser().getUserId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        CourseRating savedRating = courseRatingService.saveOrUpdateCourseRating(courseRating);
        return ResponseHandler.responseBuilder(REQUESTED_USER_TEXT,
                HttpStatus.OK, savedRating);
    }

    @GetMapping("/course/ratingswithaggregate/{courseId}")
    //http://localhost:8089/api/ratings/course/ratingswithaggregate/1
    public ResponseEntity<Object> getCourseRatingsWithAggregate(@PathVariable int courseId) {
        CourseRatingResponseDTO response = courseRatingService.getCourseRatingsWithAggregate(courseId);
        return ResponseHandler.responseBuilder(REQUESTED_USER_TEXT,
                HttpStatus.OK, response);
    }

    // Get a specific rating by ID
    @GetMapping("/{ratingId}") //http://localhost:8089/api/ratings/2
    public ResponseEntity<CourseRating> getRatingById(@PathVariable int ratingId) {
        Optional<CourseRating> rating = courseRatingService.getRatingById(ratingId);
        return rating.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Delete a course rating
    @DeleteMapping("/{ratingId}")//http://localhost:8089/api/ratings/2
    public ResponseEntity<Void> deleteCourseRating(@PathVariable int ratingId) {
        if (!courseRatingService.getRatingById(ratingId).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        courseRatingService.deleteCourseRating(ratingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
