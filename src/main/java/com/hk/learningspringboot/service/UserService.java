package com.hk.learningspringboot.service;

import com.hk.learningspringboot.dto.UserDTO;
import com.hk.learningspringboot.entity.Course;
import com.hk.learningspringboot.entity.User;
import com.hk.learningspringboot.exception.ResourceAlreadyExistsException;
import com.hk.learningspringboot.exception.ResourceNotFoundException;
import com.hk.learningspringboot.repository.ICourseRepo;
import com.hk.learningspringboot.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static com.hk.learningspringboot.utill.Constants.*;

@Service
public class UserService {

    @Autowired
    private IUserRepo userRepo;
    @Autowired
    private ICourseRepo courseRepo;

//    public String createUser(User user) {
//        try {
//            userRepo.save(user);
//            return SUCCESS_TEXT;
//        } catch (DataIntegrityViolationException e) {
//            throw new ItemAlreadyExistsException(USER_TEXT);
//        } catch (Exception e) {
//            throw new RuntimeException(INTERNAL_ERROR_TEXT);
//        }
//    }

    public String updateUser(User user) {
        userRepo.save(user);
        return SUCCESS_TEXT;
    }

    public String deleteUser(int userId) {
        userRepo.deleteById(userId);
        return SUCCESS_TEXT;
    }

    public User getUserById(int userId) {
        try {
            return userRepo.findAllByUserId(userId);
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException(USER_TEXT);
        } catch (Exception e) {
            throw new RuntimeException(INTERNAL_ERROR_TEXT);
        }
    }

    public List<User> getAllUsersWithEnrolledCourses() {
        return userRepo.findAll();
    }

    public List<UserDTO> getAllUsers() {

        return  null;
        //return userRepo.findAllUsers();
    }

    @Transactional
    public User addCourseToUser(int userId, int courseId) {
        try {
            Set<Course> courseSet = null;
            Course course = courseRepo.findById(courseId).get();
            User user = userRepo.findById(userId).get();
            //if (courseRepo.findById(courseId).isEmpty()) {}
            courseSet = user.getCourses();
            courseSet.add(course);
            user.setCourses(courseSet);
            return userRepo.save(user);
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException(USER_COURSE_TEXT);
        } catch (DataIntegrityViolationException e) {
            throw new ResourceAlreadyExistsException(USER_COURSE_TEXT);
        } catch (Exception e) {
            throw new RuntimeException(INTERNAL_ERROR_TEXT);
        }
    }

//    public User loginUser(String userEmail, String userPassword) {
//        User user = userRepo.findByUserEmail(userEmail);
//        if (user != null && user.getPassword().equals(userPassword)) {
//            return user;
//        }
//        return null;
//    }

    public boolean isUserEnrolledInCourse(int userId, int courseId) {
        return userRepo.existsByUserIdAndCourseId(userId, courseId);
    }
}
