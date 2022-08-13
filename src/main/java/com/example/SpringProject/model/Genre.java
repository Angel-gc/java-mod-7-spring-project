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
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    Seems to only work with name and not genreName?
    @NotEmpty
    private String name;

    @ManyToMany
    private Set<Book> books = new HashSet<>();

    public void addBook(Book book) {
        books.add(book);
    }
}
