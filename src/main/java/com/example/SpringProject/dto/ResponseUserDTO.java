package com.example.SpringProject.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ResponseUserDTO {
    private int id;
    private String username;
    private String password;
}
