package com.hk.learningspringboot.controller;


import com.hk.learningspringboot.service.CourseHitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coursehit")
public class CourseHitsController {
    @Autowired
    private CourseHitsService courseHitsService;

//    @PutMapping("/{courseId}") //http://localhost:8089/api/coursehit/2
//    public ResponseEntity<CourseHitcount> updateCourseHits(@PathVariable int courseId) {
//        CourseHitcount updatedCourseHitcount = courseHitsService.updateCourseHits(courseId);
//        return ResponseEntity.ok(updatedCourseHitcount);
//    }
//
//    @GetMapping("/{courseId}") //http://localhost:8089/api/coursehit/2
//    public ResponseEntity<CourseHitcount> getCourseHits(@PathVariable int courseId) {
//        CourseHitcount courseHitcount = courseHitsService.getCourseHitsByCourseId(courseId);
//        if (courseHitcount == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(courseHitcount);
//    }
//
//    @GetMapping("/course/{courseId}/count") //http://localhost:8089/api/coursehit/course/2/count
//    public ResponseEntity<Integer> getCourseHitCount(@PathVariable int courseId) {
//        int hitCount = courseHitsService.getHitCountByCourseId(courseId);
//        return ResponseEntity.ok(hitCount);
//    }
//
//    // Endpoint to create a new CourseHits record
//    @PostMapping
//    public ResponseEntity<CourseHitcount> createCourseHits(@RequestBody CourseHitcount courseHitcount) {
//        CourseHitcount createdCourseHitcount = courseHitsService.createCourseHits(courseHitcount);
//        return ResponseEntity.ok(createdCourseHitcount);
//    }

}
