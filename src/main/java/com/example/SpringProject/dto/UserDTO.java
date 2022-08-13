package com.example.SpringProject.dto;

import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private String username;
    private ReadingListDTO readingListDTO;
}
