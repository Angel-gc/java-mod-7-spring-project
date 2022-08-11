package com.example.SpringProject.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class ReadingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private LibraryMember libraryMember;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="reading_list_books",
            joinColumns=@JoinColumn(name="reading_list_id"),
            inverseJoinColumns=@JoinColumn(name="book_id"))
    private Set<Book> books = new HashSet<>();
}
