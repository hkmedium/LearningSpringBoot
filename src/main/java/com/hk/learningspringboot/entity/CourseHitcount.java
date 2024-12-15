package com.hk.learningspringboot.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "course_hitcount")
@Data
@Getter
@Setter
public class CourseHitcount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hitId")
    private int hitId;

    @OneToOne
    @JoinColumn(name = "course_id", referencedColumnName = "courseId")
    private Course course;

    @Column(name = "hitCount", nullable = false, columnDefinition = "int default 0")
    private int hitCount = 0;

    @Column(name = "lastHit", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime lastHit;
}
