package com.example.SpringProject.service;

import com.example.SpringProject.dto.*;
import com.example.SpringProject.model.LibraryMember;
import com.example.SpringProject.model.ReadingList;
import com.example.SpringProject.repository.LibraryMemberRepository;
import com.example.SpringProject.repository.ReadingListRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
public class UserService {

    @Autowired
    private LibraryMemberRepository libraryMemberRepository;
    @Autowired
    private ReadingListRepository readingListRepository;

    @Autowired
    private ModelMapper mapper;

    public CreateUserDTO create(CreateUserDTO createUserDTO){
        LibraryMember libraryMember = mapper.map(createUserDTO, LibraryMember.class);
        return mapper.map(libraryMemberRepository.save(libraryMember), CreateUserDTO.class);
    }
    public void delete(int id){
        if (libraryMemberRepository.existsById(id)){
            libraryMemberRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }
    }
    public Set<ReadingListDTO> getLists(int id){
       ResponseUserDTO responseUserDTO = libraryMemberRepository.findById(id).map(user -> mapper.map(user, ResponseUserDTO.class)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
       return responseUserDTO.getUserReadingLists();
    }

    public CreateReadingListDTO createListForUser(int id, CreateReadingListDTO createReadingListDTO){
        ResponseUserDTO responseUserDTO = libraryMemberRepository.findById(id).map(user -> mapper.map(user, ResponseUserDTO.class)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "book not found"));

        ReadingList readingList = mapper.map(createReadingListDTO, ReadingList.class);
        readingList = readingListRepository.save(readingList);

        responseUserDTO.getUserReadingLists().add(readingList);
    }


}
