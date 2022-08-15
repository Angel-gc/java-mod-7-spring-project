package com.example.SpringProject.repository;

import com.example.SpringProject.model.Book;
import com.example.SpringProject.model.ReadingList;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;


public interface BookRepository extends JpaRepository<Book, Integer> {
}
