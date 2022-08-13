package com.example.SpringProject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "`User`")
@Getter
@Setter
public class LibraryMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String username;
    @NotBlank
    private String password;

    @OneToMany(mappedBy = "libraryMember")
    Set<ReadingList> readingLists = new HashSet<>();
}
