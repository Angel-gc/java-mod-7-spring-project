package com.example.SpringProject.repository;

import com.example.SpringProject.model.ReadingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingListRepository extends JpaRepository<ReadingList, Integer> {
}
