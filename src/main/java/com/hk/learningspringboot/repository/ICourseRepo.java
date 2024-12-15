package com.hk.learningspringboot.repository;

import com.hk.learningspringboot.dto.CourseDTO;
import com.hk.learningspringboot.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICourseRepo extends JpaRepository<Course, Integer> {
    //Course findAllByCourseId(int courseId);

    List<Course> getCoursesByCategoryCategoryId(int categoryId);

    @Query("SELECT DISTINCT new com.hk.learningspringboot.dto.CourseDTO(c.courseId, c.courseName, c.courseDescription, ca.categoryName, ca.categoryId) " +
            "FROM Course c JOIN c.category ca " +
            "WHERE ca.categoryId = :categoryId")
    List<CourseDTO> findCoursesByCategoryId(@Param("categoryId") int categoryId);

//    @Query("SELECT new com.hk.learningspringboot.dto.UserDTO(u.userId, u.username, u.userEmail, u.userPhoneNo, u.userAvater, u.role) " +
//            "FROM Course c JOIN c.users u WHERE c.courseId = :courseId")
//    List<UserDTO> findUsersByCourseId(@Param("courseId") int courseId);
    @Query("SELECT new com.hk.learningspringboot.dto.CourseDTO(c.courseId, c.courseName, c.courseDescription, COALESCE(ch.hitCount, 0)) " +
            "FROM Course c LEFT JOIN c.courseHitcount ch " +
            "WHERE c.courseId = :courseId")
    CourseDTO findCourseWithHitCountById(@Param("courseId") int courseId);

}
