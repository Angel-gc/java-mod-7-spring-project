package com.example.SpringProject.service;

import com.example.SpringProject.dto.*;
import com.example.SpringProject.model.Book;
import com.example.SpringProject.model.LibraryMember;
import com.example.SpringProject.model.ReadingList;
import com.example.SpringProject.repository.LibraryMemberRepository;
import com.example.SpringProject.repository.ReadingListRepository;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
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

    public ResponseUserDTO getLists(int id){
       LibraryMember user = libraryMemberRepository.findById(id).get();

           return mapper.map(user, ResponseUserDTO.class);
    }

    public ResponseUserDTO createListForUser(int id, CreateReadingListDTO createReadingListDTO){
        LibraryMember user = libraryMemberRepository.findById(id).get();
        Set<ResponseBookDTO> responseBookDTOS = createReadingListDTO.getListOfBooksToRead();
        ReadingList readingList = mapper.map(responseBookDTOS, ReadingList.class);
        readingList.setName(createReadingListDTO.getName());
        Set<Book> listOfBooksToRead = new HashSet<>();
        for (ResponseBookDTO b: responseBookDTOS){
            listOfBooksToRead.add(mapper.map(b, Book.class));
        }
        readingList.setBooks(listOfBooksToRead);
        readingList.setBooks(listOfBooksToRead);
        readingListRepository.save(readingList);
        user.getReadingLists().add(readingList);

        return mapper.map(libraryMemberRepository.save(user), ResponseUserDTO.class);
    }

    public ReadingListDTO getList(int id, int list_id){
       ResponseUserDTO responseUserDTO =  libraryMemberRepository.findById(id)
                .map(member -> mapper.map(member, ResponseUserDTO.class))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
       try {
           return responseUserDTO.getReadingLists()
                   .stream()
                   .filter(list -> list.getId() == list_id)
                   .collect(Collectors.toList()).get(0);
       } catch (IndexOutOfBoundsException e){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "reading list not found");
       }
    }
}
