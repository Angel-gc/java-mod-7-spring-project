package com.example.SpringProject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Set<Book> books = new HashSet<>();
}
