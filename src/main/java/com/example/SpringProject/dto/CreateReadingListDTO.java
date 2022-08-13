package com.example.SpringProject.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
public class CreateReadingListDTO {
    private int id;
    @NotNull
    private String name;
    private Set<ResponseBookDTO> listOfBooksToRead = new HashSet<>();
}
