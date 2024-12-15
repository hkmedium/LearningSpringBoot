package com.hk.learningspringboot.service;

import com.hk.learningspringboot.repository.ICourseHitcountRepo;
import com.hk.learningspringboot.repository.ICourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseHitsService {
    @Autowired
    private ICourseHitcountRepo courseHitsRepo;

    @Autowired
    private ICourseRepo courseRepo;
//
//    public CourseHitcount getCourseHitsByCourseId(int courseId) {
//        Optional<CourseHitcount> courseHits = courseHitsRepo.findById(courseId);
//        return courseHits.orElse(null);
//    }
//    public CourseHitcount updateCourseHits(int courseId) {
//        try {
//            Course course = courseRepo.findById(courseId).get();
//            CourseHitcount courseHitcount = getCourseHitsByCourseId(courseId);
//
//            if (courseHitcount != null) {
//                courseHitcount.setHitCount(courseHitcount.getHitCount() + 1);
//                courseHitcount.setLastHit(LocalDateTime.now());
//            } else {
//                courseHitcount = new CourseHitcount();
//                courseHitcount.setCourse(course);
//                courseHitcount.setHitCount(1);
//                courseHitcount.setLastHit(LocalDateTime.now());
//            }
//
//            return courseHitsRepo.save(courseHitcount);
//        } catch (Exception e) {
//            throw new RuntimeException(INTERNAL_ERROR_TEXT);
//        }
//    }
//
//    public CourseHitcount createCourseHits(CourseHitcount courseHitcount) {
//        return courseHitsRepo.save(courseHitcount);
//    }
//
//    public int getHitCountByCourseId(int courseId) {
//        Integer hitCount = courseHitsRepo.findHitCountByCourseId(courseId);
//        return hitCount != null ? hitCount : 0;
//    }
}
