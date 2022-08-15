package com.example.SpringProject.repository;

import com.example.SpringProject.model.ReadingList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ReadingListRepository extends JpaRepository<ReadingList, Integer> {
    Set<ReadingList> findAllByLibraryMemberId(Integer libraryMemberId);
}
