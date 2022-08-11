package com.example.SpringProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
public class CreateBookDTO {
    @NotEmpty
    private String title;
    @Positive
    private int pages;
}
