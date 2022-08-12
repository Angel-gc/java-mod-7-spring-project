package com.example.SpringProject.dto;

import com.example.SpringProject.model.Book;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ReadingListDTO {
    private int id;
    private String name;
    private Set<BookDTO> booksInList = new HashSet<>();
}
