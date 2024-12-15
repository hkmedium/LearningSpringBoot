package com.hk.learningspringboot.repository;

import com.hk.learningspringboot.dto.CourseRatingAggregateDTO;
import com.hk.learningspringboot.dto.CourseRatingUserDTO;
import com.hk.learningspringboot.entity.CourseRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICourseRatingRepo extends JpaRepository<CourseRating, Integer> {

    boolean existsByCourseCourseIdAndUserUserId(int courseId, int userId);

    @Query("SELECT new com.hk.learningspringboot.dto.CourseRatingAggregateDTO(cr.course.courseId, AVG(cr.rating), COUNT(cr.rating)) " +
            "FROM CourseRating cr " +
            "WHERE cr.course.courseId = :courseId")
    CourseRatingAggregateDTO findCourseRatingAggregate(@Param("courseId") int courseId);

    @Query("SELECT new com.hk.learningspringboot.dto.CourseRatingUserDTO(u.userId, u.username, u.userAvater, cr.comment, cr.rating) " +
            "FROM CourseRating cr " +
            "JOIN cr.user u " +
            "WHERE cr.course.courseId = :courseId")
    List<CourseRatingUserDTO> findCourseRatingUsers(@Param("courseId") int courseId);
}
