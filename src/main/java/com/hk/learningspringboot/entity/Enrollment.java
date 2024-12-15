//package com.hk.learningspringboot.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "enrollments")
//public class Enrollment {
//    //    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    @Column(name = "enrollmentId")
////    private int enrollmentId;
//    @EmbeddedId
//    private EnrollmentId enrollmentId;
//    @Column(name = "enrollmentDate", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
//    private LocalDateTime enrollmentDate;
//
//    @ManyToOne
//    @MapsId("userId")
//    @JoinColumn(name = "userId", nullable = false)
//    private User user;
//
//    @ManyToOne
//    @MapsId("courseId")
//    @JoinColumn(name = "courseId", nullable = false)
//    private Course course;
//
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Course getCourse() {
//        return course;
//    }
//
//    public void setCourse(Course course) {
//        this.course = course;
//    }
//}
