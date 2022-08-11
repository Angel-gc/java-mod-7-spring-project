package com.example.SpringProject.dto;

import com.example.SpringProject.model.Book;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class GenreBooksDTO {
    private int id;
    private String name;
    Set<BookDTO> genreBooks = new HashSet<>();
}
