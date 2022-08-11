package com.example.SpringProject.dto;

import com.example.SpringProject.model.Book;

import java.util.HashSet;
import java.util.Set;

public class ReadingListDTO {
    private int id;
    private String name;
    private Set<BookDTO> readingList = new HashSet<>();
}
