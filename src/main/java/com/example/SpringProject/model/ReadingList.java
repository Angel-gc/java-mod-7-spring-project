package com.example.SpringProject.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class ReadingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private LibraryMember libraryMember;

    @ManyToMany(mappedBy = "readingLists", fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Book> books = new HashSet<>();

    public void addBook(Book book){
        books.add(book);
    }
}
