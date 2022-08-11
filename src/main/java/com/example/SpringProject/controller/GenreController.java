package com.example.SpringProject.controller;

import com.example.SpringProject.dto.BookDTO;
import com.example.SpringProject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/genre")
public class GenreController {
    @Autowired
    private BookService bookService;

    @GetMapping("/{id}/books")
    public Set<BookDTO> getBooks(@PathVariable int id){
        return bookService.getGenreBooks(id);
    }
}
