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
import java.util.stream.Collectors;

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

    public ReadingListDTO createListForUser(int id, CreateReadingListDTO createReadingListDTO){
        ResponseUserDTO responseUserDTO = libraryMemberRepository.findById(id).map(user -> mapper.map(user, ResponseUserDTO.class)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));

        ReadingList readingList = mapper.map(createReadingListDTO, ReadingList.class);
        ReadingListDTO readingListDTO = mapper.map(readingList, ReadingListDTO.class);
        readingList.setName(createReadingListDTO.getName());
        readingList = readingListRepository.save(readingList);

        responseUserDTO.getUserReadingLists().add(mapper.map(readingList, ReadingListDTO.class));
        return readingListDTO;
    }

    public ReadingListDTO getList(int id, int list_id){
       ResponseUserDTO responseUserDTO =  libraryMemberRepository.findById(id)
                .map(member -> mapper.map(member, ResponseUserDTO.class))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
       try {
           return responseUserDTO.getUserReadingLists()
                   .stream()
                   .filter(list -> list.getId() == list_id)
                   .collect(Collectors.toList()).get(0);
       } catch (IndexOutOfBoundsException e){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "reading list not found");
       }
//      return readingListRepository.findById(getUserListDTO.getList_id()).map(list -> mapper.map(list, ReadingListDTO.class)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user reading list not found"));
    }


}
