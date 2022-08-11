package com.example.SpringProject.repository;

import com.example.SpringProject.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
