package com.example.SpringProject.dto;

import com.example.SpringProject.model.Author;
import com.example.SpringProject.model.Genre;
import com.example.SpringProject.model.ReadingList;
import lombok.Data;
import lombok.Generated;

import java.util.HashSet;
import java.util.Set;

@Data
public class BookDTO {
    private int id;
    private String title;
    private int pages;
    private Author author;
    private Set<GenreDTO> genres = new HashSet<>();
}
