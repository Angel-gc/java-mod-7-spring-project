package com.example.SpringProject.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class CreateUserDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
