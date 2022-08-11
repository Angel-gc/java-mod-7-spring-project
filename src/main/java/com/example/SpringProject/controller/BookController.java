package com.example.SpringProject.controller;

import com.example.SpringProject.dto.BookDTO;
import com.example.SpringProject.dto.CreateBookDTO;
import com.example.SpringProject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookDTO> getBooks(){
        return bookService.getAllBooks();
    }
    @GetMapping("/{id}")
    public BookDTO getBook(@PathVariable int id){
        return bookService.getBook(id);
    }
    @PostMapping
    public CreateBookDTO createBook(@Valid @RequestBody CreateBookDTO createBookDTO) {
        return bookService.create(createBookDTO);
    }
    @PutMapping("/{id}")
    public void updateBook(@PathVariable int id){

    }
    @DeleteMapping("/{id}")
    public void deleteBook(int id){
        bookService.deleteBook(id);
    }
}
