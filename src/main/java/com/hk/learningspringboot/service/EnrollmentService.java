//package com.hk.learningspringboot.service;
//
//import com.hk.learningspringboot.entity.Course;
//import com.hk.learningspringboot.entity.Enrollment;
//import com.hk.learningspringboot.entity.User;
//import com.hk.learningspringboot.exception.ItemAlreadyExistsException;
//import com.hk.learningspringboot.exception.ItemNotFoundException;
//import com.hk.learningspringboot.repository.ICourseRepo;
//import com.hk.learningspringboot.repository.IEnrollmentRepo;
//import com.hk.learningspringboot.repository.IUserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.stereotype.Service;
//
//import java.util.NoSuchElementException;
//import java.util.Optional;
//
//@Service
//public class EnrollmentService {
//    @Autowired
//    private IEnrollmentRepo enrollmentRepo;
//    @Autowired
//    private IUserRepo userRepo;
//    @Autowired
//    private ICourseRepo courseRepo;
//
//    public String enrollUserInCourse(int userId, int courseId) {
//        try {
//            User user = userRepo.findById(userId).get();
//            Course course = courseRepo.findById(courseId).get();
//            Enrollment enrollment = new Enrollment();
//            enrollment.setUser(user);
//            enrollment.setCourse(course);
//            enrollmentRepo.save(enrollment);
//            return "Enrollment Created Successfully";
//
//        } catch (NoSuchElementException e) {
//            throw new ItemNotFoundException("Requested User or Course");
//        } catch (DataIntegrityViolationException e) {
//            throw new ItemAlreadyExistsException("Requested Enrollment");
//        } catch (Exception e) {
//            throw new RuntimeException("Internal error happened!");
//        }
//    }
//
//    public Optional<Enrollment> getEnrollment(int userId, int courseId) {
//        return enrollmentRepo.findEnrollmentByUserAndCourse(userId, courseId);
//    }
//
//    public boolean isUserEnrolled(int userId, int courseId) {
//        return enrollmentRepo.existsByUserIdAndCourseId(userId, courseId);
//    }
//}
