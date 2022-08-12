package com.example.SpringProject.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class GenreBooksDTO {
    private int id;
    private String name;
    Set<ResponseBookDTO> genreBooks = new HashSet<>();
}
