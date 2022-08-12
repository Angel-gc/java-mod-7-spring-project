package com.example.SpringProject.controller;

import com.example.SpringProject.dto.ResponseBookDTO;
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
    public List<ResponseBookDTO> getBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseBookDTO getBook(@PathVariable int id){
        return bookService.getBook(id);
    }

    @PostMapping
    public CreateBookDTO createBook(@Valid @RequestBody CreateBookDTO createBookDTO) {
        return bookService.create(createBookDTO);
    }

    @PutMapping("/{id}")
    public CreateBookDTO updateBook(@PathVariable int id, @RequestBody CreateBookDTO createBookDTO){
        return bookService.updateBook(id, createBookDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id){
        bookService.deleteBook(id);
    }
}
