package com.hk.learningspringboot.repository;

import com.hk.learningspringboot.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorRepo extends JpaRepository<Author, Integer> {
}
