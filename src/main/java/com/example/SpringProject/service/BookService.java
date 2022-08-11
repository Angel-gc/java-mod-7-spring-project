package com.example.SpringProject.service;

import com.example.SpringProject.dto.BookDTO;
import com.example.SpringProject.dto.CreateBookDTO;
import com.example.SpringProject.model.*;
import com.example.SpringProject.repository.AuthorRepository;
import com.example.SpringProject.repository.BookRepository;
import com.example.SpringProject.repository.GenreRepository;
import com.example.SpringProject.repository.LibraryMemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ModelMapper mapper;

    public BookDTO create(CreateBookDTO createBookDTO){
        Book book = mapper.map(createBookDTO, Book.class);
        return mapper.map(bookRepository.save(book), BookDTO.class);
    }
    public List<BookDTO> getAllBooks(){
        return bookRepository.findAll().stream().map(book -> mapper.map(book, BookDTO.class)).collect(Collectors.toList());
    }
    public BookDTO getBook(int id){
        return bookRepository.findById(id)
                .map(book -> mapper.map(book, BookDTO.class)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "camper not found"));
    }
    public void updateBook(int id){
        BookDTO bookDTO = getBook(id);
        Book book = mapper.map(bookDTO, Book.class);
        book.setAuthor(authorRepository.findById(bookDTO.getAuthor().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "author to set was not found")));
        book.setGenres(genreRepository.findById(BookDTO));

    }
    public void deleteBook(int id){
        if (bookRepository.existsById(id)){
            bookRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "camper not found");
        }
    }

}
