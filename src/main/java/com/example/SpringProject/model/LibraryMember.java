package com.example.SpringProject.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "`User`")
@Getter
@Setter
@ToString
public class LibraryMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String username;
    @NotBlank
    private String password;

    @OneToMany(mappedBy = "libraryMember")
    @ToString.Exclude
    Set<ReadingList> readingLists = new HashSet<>();

    public void addReadingList(ReadingList readingList){
        readingLists.add(readingList);
    }
}
