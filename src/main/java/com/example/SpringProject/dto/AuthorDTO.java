package com.example.SpringProject.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AuthorDTO {
    private int id;
    private String name;
}
