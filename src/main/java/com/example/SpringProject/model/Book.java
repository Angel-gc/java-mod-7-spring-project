package com.example.SpringProject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NotBlank
    private String title;
    @Positive
    private int pages;
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate published;

    @ManyToOne
    @JoinColumn(name="author_id")
    private Author author;

    @ManyToMany
    @JoinTable(name="book_genres",
            joinColumns=@JoinColumn(name="book_id"),
            inverseJoinColumns=@JoinColumn(name="genre_id"))
    @ToString.Exclude
    private Set<Genre> genres = new HashSet<>();
}
