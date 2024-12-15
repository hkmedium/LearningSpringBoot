package com.hk.learningspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserCourseDTO {
    private int userId;
    private String userName;
    private int courseId;
    private String courseName;
    private LocalDateTime enrollmentDate;
}
