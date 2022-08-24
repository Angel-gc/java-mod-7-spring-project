package com.example.SpringProject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

import java.util.HashSet;
import java.util.Set;

@Data
public class CreateBookDTO {
    @NotBlank
    private String title;
    @Positive
    private int pages;
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate published;

    private AuthorDTO author;
    private Set<GenreDTO> genres = new HashSet<>();
}
