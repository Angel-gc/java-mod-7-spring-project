package com.example.SpringProject.service;

import com.example.SpringProject.dto.*;
import com.example.SpringProject.model.Book;
import com.example.SpringProject.model.LibraryMember;
import com.example.SpringProject.model.ReadingList;
import com.example.SpringProject.repository.BookRepository;
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
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private LibraryMemberRepository libraryMemberRepository;
    @Autowired
    private ReadingListRepository readingListRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookService bookService;

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
    //problem with users not having any books saved into their reading list
    public Set<ReadingListDTO> getLists(int id){
        Set<ReadingList> readingLists = readingListRepository.findAllByLibraryMemberId(id);
        Set<ReadingListDTO> readingListDTOS = new HashSet<>();
        for (ReadingList readingList: readingLists){
            readingListDTOS.add(mapper.map(readingList, ReadingListDTO.class));
        }
        return readingListDTOS;
    }

    public ResponseUserDTO createListForUser(int id, CreateReadingListDTO createReadingListDTO){
        try {
            LibraryMember user = libraryMemberRepository.findById(id).get();
            Set<ResponseBookDTO> responseBookDTOS = createReadingListDTO.getNewList();
            ReadingList readingList = mapper.map(responseBookDTOS, ReadingList.class);
            readingList.setName(createReadingListDTO.getName());
            Set<Book> booksToSet = new HashSet<>();

            for (ResponseBookDTO r : responseBookDTOS) {
                Book book = mapper.map(r, Book.class);
                if (!bookRepository.existsById(book.getId())) {
                    CreateBookDTO createBookDTO = bookService.create(mapper.map(book, CreateBookDTO.class));
                    book = mapper.map(createBookDTO, Book.class);
                }
                readingList.addBook(book);
                booksToSet.add(book);
            }
            readingList.setBooks(booksToSet);
            readingList.setLibraryMember(user);
            readingListRepository.save(readingList);
            user.addReadingList(readingList);
            libraryMemberRepository.save(user);
            return mapper.map(user, ResponseUserDTO.class);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }
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
