package com.hk.learningspringboot.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CourseRatingUserDTO {
    private int userId;
    private String username;
    private String userAvater;
    private String comment;
    private int rating;
}
