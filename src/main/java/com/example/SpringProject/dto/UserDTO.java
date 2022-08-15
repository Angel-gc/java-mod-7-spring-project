package com.example.SpringProject.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private int id;
    private String username;
    private Set<ReadingListDTO> readingLists;
}
