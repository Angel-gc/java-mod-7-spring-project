package com.example.SpringProject.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateReadingListDTO {
    @NotEmpty
    private String name;
}
