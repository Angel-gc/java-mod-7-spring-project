package com.example.SpringProject.dto;

import com.example.SpringProject.model.Author;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
