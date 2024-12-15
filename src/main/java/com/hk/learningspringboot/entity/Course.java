package com.hk.learningspringboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "Courses")
@Getter
@Setter
@AllArgsConstructor

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseId")
    private int courseId;

    @Column(name = "courseName", length = 50, nullable = false)
    private String courseName;

    @Column(name = "courseDescription", length = 250)
    private String courseDescription;

    @Column(name = "courseBanner", length = 200)
    private String courseBanner;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "categoryId")
    private Category category;

    @JsonIgnore
    @ManyToMany(mappedBy = "courses")
    private Set<User> users;

    @JsonIgnore
    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL)
    private CourseHitcount courseHitcount;
}
