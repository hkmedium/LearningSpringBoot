package com.hk.learningspringboot.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Authors")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authorId")
    private int authorId;

    @Column(name = "authorName", length = 45, nullable = false)
    private String authorName;

    @Column(name = "authorEmail", length = 45, nullable = false, unique = true)
    private String authorEmail;

    @Column(name = "authorPhoneNo", length = 15)
    private String authorPhoneNo;

    @Column(name = "authorPassword", length = 45, nullable = false)
    private String authorPassword;

    @Column(name = "authorAvater", length = 200)
    private String authorAvater;
}

