package com.example.SpringProject.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ReadingListDTO {
    private int id;
    private String name;
    private Set<ResponseBookDTO> listOfBooksToRead = new HashSet<>();
}
