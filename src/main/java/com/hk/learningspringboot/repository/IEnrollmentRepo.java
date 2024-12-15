//package com.hk.learningspringboot.repository;
//
//import com.hk.learningspringboot.dto.UserCourseDTO;
//import com.hk.learningspringboot.entity.Enrollment;
//import com.hk.learningspringboot.entity.EnrollmentId;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface IEnrollmentRepo extends JpaRepository<Enrollment, EnrollmentId> {
//    @Query("SELECT new com.hk.learningspringboot.dto.UserCourseDTO(u.userId, u.userName, c.courseId, c.courseName, e.enrollmentDate) " +
//            "FROM User u JOIN u.enrollments e JOIN e.course c " +
//            "WHERE u.userId = :userId")
//    List<UserCourseDTO> findUserCourses(@Param("userId") int userId);
//
//    @Query("SELECT e FROM Enrollment e WHERE e.user.userId = :userId AND e.course.courseId = :courseId")
//    Optional<Enrollment> findEnrollmentByUserAndCourse(@Param("userId") int userId, @Param("courseId") int courseId);
//
//    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END " +
//            "FROM Enrollment e WHERE e.user.userId = :userId AND e.course.courseId = :courseId")
//    boolean existsByUserIdAndCourseId(@Param("userId") int userId, @Param("courseId") int courseId);
//}
