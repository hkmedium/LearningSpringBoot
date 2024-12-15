package com.hk.learningspringboot.service;

import com.hk.learningspringboot.dto.UserDTO;
import com.hk.learningspringboot.entity.Category;
import com.hk.learningspringboot.entity.Course;
import com.hk.learningspringboot.dto.CourseDTO;
import com.hk.learningspringboot.entity.CourseHitcount;
import com.hk.learningspringboot.exception.ResourceAlreadyExistsException;
import com.hk.learningspringboot.exception.ResourceNotFoundException;
import com.hk.learningspringboot.repository.ICategoryRepo;
import com.hk.learningspringboot.repository.ICourseHitcountRepo;
import com.hk.learningspringboot.repository.ICourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static com.hk.learningspringboot.utill.Constants.*;

@Service
public class CourseService {

    @Autowired
    private ICourseRepo courseRepo;

    @Autowired
    private ICategoryRepo categoryRepo;

    @Autowired
    private ICourseHitcountRepo hitcountRepo;

    public String createCourse(Course course, int categoryId) {
        try {
            Category category = categoryRepo.findById(categoryId).get();
            course.setCategory(category);
            courseRepo.save(course);
            return SUCCESS_TEXT;
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(COURSE_TEXT);
        } catch (DataIntegrityViolationException e) {
            throw new ResourceAlreadyExistsException(COURSE_TEXT);
        } catch (Exception e) {
            throw new RuntimeException(INTERNAL_ERROR_TEXT);
        }
    }

//    public Course saveCourseWithCategories(Course course) {
//        // For each category in the course, establish the bi-directional relationship
//        for (Category category : course.getCategories()) {
//            category.getCourses().add(course);
//        }
//
//        // Save the course along with categories
//        return courseRepo.save(course);
//    }

    public String updateCourse(Course course) {
        courseRepo.save(course);
        return SUCCESS_TEXT;
    }

    public String deleteCourse(int courseId) {
        courseRepo.deleteById(courseId);
        return SUCCESS_TEXT;
    }

    public Course getCourseById(int courseId) {
        try {
            return courseRepo.findById(courseId).get();
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException(COURSE_TEXT);
        } catch (Exception e) {
            throw new RuntimeException(INTERNAL_ERROR_TEXT);
        }
    }

    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    public List<Course> getCoursesByCategoryId(int categoryId) {
        return courseRepo.getCoursesByCategoryCategoryId(categoryId);
    }

    public List<CourseDTO> getCoursesByCategoryIdRawQuery(int categoryId) {
        return courseRepo.findCoursesByCategoryId(categoryId);
    }

    public List<UserDTO> getUsersByCourseId(int courseId) {
        return  null;
        //return courseRepo.findUsersByCourseId(courseId);
    }

    public CourseHitcount updateCourseHitsCount(int courseId) {
        try {
            Course course = courseRepo.findById(courseId).get();
            CourseHitcount courseHitcount = course.getCourseHitcount();
            if (courseHitcount == null) {
                courseHitcount = new CourseHitcount();
                courseHitcount.setCourse(course);
            }
            courseHitcount.setHitCount(courseHitcount.getHitCount() + 1);
            courseHitcount.setLastHit(LocalDateTime.now());
            return hitcountRepo.save(courseHitcount);
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException(COURSE_TEXT);
        } catch (Exception e) {
            throw new RuntimeException(INTERNAL_ERROR_TEXT);
        }
    }

    public int getCourseHitCount(int courseId) {
        Course course = courseRepo.findById(courseId).get();
        CourseHitcount courseHitcount = course.getCourseHitcount();
        if (courseHitcount != null) {
            return courseHitcount.getHitCount();
        }

        throw new RuntimeException("Course hit count not found for courseId: " + courseId);
    }

    public CourseDTO getCourseDetails(int courseId) {
        try {
            CourseDTO course =  courseRepo.findCourseWithHitCountById(courseId);
            return course;
        } catch (Exception e){
            throw new RuntimeException(INTERNAL_ERROR_TEXT);
        }
    }
}
