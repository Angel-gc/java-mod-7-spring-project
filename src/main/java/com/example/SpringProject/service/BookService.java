package com.example.SpringProject.service;

import com.example.SpringProject.dto.BookDTO;
import com.example.SpringProject.dto.CreateBookDTO;
import com.example.SpringProject.dto.GenreBooksDTO;
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
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ModelMapper mapper;

    public CreateBookDTO create(CreateBookDTO createBookDTO){
        Book book = mapper.map(createBookDTO, Book.class);
        book = bookRepository.save(book);
        return mapper.map(book, CreateBookDTO.class);
    }
    public List<BookDTO> getAllBooks(){
        return bookRepository.findAll().stream().map(book -> mapper.map(book, BookDTO.class)).collect(Collectors.toList());
    }
    public BookDTO getBook(int id){
        return bookRepository.findById(id)
                .map(book -> mapper.map(book, BookDTO.class)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "book not found"));
    }
    public void deleteBook(int id){
        if (bookRepository.existsById(id)){
            bookRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "book not found");
        }
    }
    public Set<BookDTO> getGenreBooks(int id){
        GenreBooksDTO genre = genreRepository.findById(id)
                .map(g -> mapper.map(g, GenreBooksDTO.class))
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "genre not found"));
        return genre.getGenreBooks();
    }
    public BookDTO updateBook(int id){
        BookDTO bookDTO = getBook(id);
        Book book = mapper.map(bookDTO, Book.class);
        book.setId(id);
        bookDTO.setId(id);

        return mapper.map(bookRepository.save(book), BookDTO.class);
    }
}
