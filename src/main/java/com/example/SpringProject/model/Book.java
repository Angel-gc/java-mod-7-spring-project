package com.example.SpringProject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.HashSet;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="author_id")
    private Author author;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<ReadingList> readingLists = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="book_genres",
            joinColumns=@JoinColumn(name="book_id"),
            inverseJoinColumns=@JoinColumn(name="genre_id"))
    @ToString.Exclude
    private Set<Genre> genres = new HashSet<>();
}
