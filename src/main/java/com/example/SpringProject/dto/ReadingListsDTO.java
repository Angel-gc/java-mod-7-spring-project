package com.example.SpringProject.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;
@Data
public class ReadingListsDTO {
    private Set<ReadingListDTO> readingLists = new HashSet<>();
}
