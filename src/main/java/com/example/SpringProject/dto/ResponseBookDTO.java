package com.example.SpringProject.dto;

import com.example.SpringProject.model.Author;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class ResponseBookDTO {
    private int id;
    private String title;
    private int pages;
    private LocalDate published;
    private AuthorDTO author;
    private Set<GenreDTO> genres = new HashSet<>();
}
