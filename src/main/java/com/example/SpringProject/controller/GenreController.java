package com.example.SpringProject.controller;


import com.example.SpringProject.dto.GenreBooksDTO;

import com.example.SpringProject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/genre")
public class GenreController {
    @Autowired
    private BookService bookService;

    @GetMapping("/{id}/books")
    public GenreBooksDTO getBooks(@PathVariable int id){
        return bookService.getGenreBooks(id);
    }
}
