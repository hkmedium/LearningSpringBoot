package com.hk.learningspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class CourseRatingAggregateDTO {
    private int courseId;
    private double avgRating;
    private long ratingCount;
}
