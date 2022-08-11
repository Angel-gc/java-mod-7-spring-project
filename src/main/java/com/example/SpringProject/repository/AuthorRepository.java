package com.example.SpringProject.repository;

import com.example.SpringProject.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
