package com.example.SpringProject.repository;

import com.example.SpringProject.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Integer> {
}
