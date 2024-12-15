package com.hk.learningspringboot.service;

import com.hk.learningspringboot.dto.CourseRatingAggregateDTO;
import com.hk.learningspringboot.dto.CourseRatingResponseDTO;
import com.hk.learningspringboot.dto.CourseRatingUserDTO;
import com.hk.learningspringboot.entity.CourseRating;
import com.hk.learningspringboot.repository.ICourseRatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseRatingService {
    @Autowired
    private ICourseRatingRepo courseRatingRepo;

    // Save or update a course rating
    public CourseRating saveOrUpdateCourseRating(CourseRating courseRating) {
        return courseRatingRepo.save(courseRating);
    }

    // Get a specific rating by its ID
    public Optional<CourseRating> getRatingById(int ratingId) {
        return courseRatingRepo.findById(ratingId);
    }

    // Delete a course rating
    public void deleteCourseRating(int ratingId) {
        courseRatingRepo.deleteById(ratingId);
    }

    // Check if a rating already exists for a specific course and user
    public boolean existsByCourseAndUser(int courseId, int userId) {
        return courseRatingRepo.existsByCourseCourseIdAndUserUserId(courseId, userId);
    }

    public CourseRatingResponseDTO getCourseRatingsWithAggregate(int courseId) {
        CourseRatingAggregateDTO aggregateData = courseRatingRepo.findCourseRatingAggregate(courseId);
        List<CourseRatingUserDTO> userComments = courseRatingRepo.findCourseRatingUsers(courseId);
        return new CourseRatingResponseDTO(aggregateData, userComments);
    }

}
