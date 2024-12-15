package com.hk.learningspringboot.repository;

import com.hk.learningspringboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepo extends JpaRepository<User, Integer> {

    User findAllByUserId(int userId); //userId

//    @Query("SELECT new com.hk.learningspringboot.dto.UserDTO(u.userId, u.username, u.userEmail, u.userPhoneNo, u.userAvater, u.role) " +
//            "FROM User u")
//    List<UserDTO> findAllUsers();

    User findByUserEmail(String userEmail);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END " +
            "FROM User u JOIN u.courses c " +
            "WHERE u.userId = :userId AND c.courseId = :courseId")
    boolean existsByUserIdAndCourseId(@Param("userId") int userId, @Param("courseId") int courseId);

    //this is for auth need to change
    Optional<User> findByUsername(String username);
}
