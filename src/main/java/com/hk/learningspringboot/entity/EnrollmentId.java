//package com.hk.learningspringboot.entity;
//
//import jakarta.persistence.Embeddable;
//
//import java.io.Serializable;
//import java.util.Objects;
//
//@Embeddable
//public class EnrollmentId implements Serializable {
//    private int userId;
//    private int courseId;
//
//    // Getters, setters, equals, and hashCode methods
//
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        EnrollmentId that = (EnrollmentId) o;
//        return userId == that.userId && courseId == that.courseId;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(userId, courseId);
//    }
//
//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//
//    public int getCourseId() {
//        return courseId;
//    }
//
//    public void setCourseId(int courseId) {
//        this.courseId = courseId;
//    }
//}