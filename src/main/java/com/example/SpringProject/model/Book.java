package com.example.SpringProject.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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
    private int title;
    @Positive
    private int pages;
    @NotNull
    private LocalDateTime published;

    @ManyToOne
    @JoinColumn(name="author_id", nullable=false)
    private Author author;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="book_genres",
            joinColumns=@JoinColumn(name="book_id"),
            inverseJoinColumns=@JoinColumn(name="genre_id"))
    @ToString.Exclude
    private Set<Genre> genres = new HashSet<>();
}
