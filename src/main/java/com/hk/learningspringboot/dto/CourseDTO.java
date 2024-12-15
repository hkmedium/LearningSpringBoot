package com.hk.learningspringboot.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CourseDTO {
    private int courseId;
    private String courseName;
    private String courseDescription;
    private String categoryName;
    private int categoryId;
    private int hitCount;

    public CourseDTO(int courseId, String courseName, String courseDescription, String categoryName, int categoryId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.categoryName = categoryName;
        this.categoryId = categoryId;
    }

    public CourseDTO(int courseId, String courseName, String courseDescription, int hitCount) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.hitCount = hitCount;
    }
}
