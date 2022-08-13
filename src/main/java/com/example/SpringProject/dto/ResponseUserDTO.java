package com.example.SpringProject.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
public class ResponseUserDTO {
    private int id;
    private String username;
    private Set<ReadingListDTO> readingLists = new HashSet<>();
}
