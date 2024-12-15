package com.hk.learningspringboot.repository;

import com.hk.learningspringboot.entity.CourseHitcount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseHitcountRepo extends JpaRepository<CourseHitcount, Integer> {
//    @Query("SELECT ch.hitCount FROM CourseHits ch WHERE ch.course.courseId = :courseId")
//    Integer findHitCountByCourseId(@Param("courseId") int courseId);
}
