package com.example.SpringProject.dto;

import javax.validation.constraints.NotEmpty;

public class AuthorDTO {
    private int id;
    @NotEmpty
    private String name;
}
