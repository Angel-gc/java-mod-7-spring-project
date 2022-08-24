package com.example.SpringProject.repository;

import com.example.SpringProject.model.LibraryMember;
import org.springframework.data.jpa.repository.JpaRepository;



public interface LibraryMemberRepository extends JpaRepository<LibraryMember, Integer> {
}