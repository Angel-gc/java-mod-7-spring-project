package com.example.SpringProject.service;

import com.example.SpringProject.dto.*;
import com.example.SpringProject.model.*;

import com.example.SpringProject.repository.BookRepository;
import com.example.SpringProject.repository.GenreRepository;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
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
        AuthorDTO authorDTO = createBookDTO.getAuthor();
        Author author = mapper.map(authorDTO, Author.class);
        book.setAuthor(author);
        Set<GenreDTO> genreDTOS = createBookDTO.getGenres();
        Set<Genre> genresToSet =new HashSet<>();
        for (GenreDTO g: genreDTOS ){
           Genre genre = mapper.map(g, Genre.class);
           genre.addBook(book);
           genresToSet.add(genre);
        }
        book.setGenres(genresToSet);
        book = bookRepository.save(book);
        return mapper.map(book, CreateBookDTO.class);
    }

    public List<ResponseBookDTO> getAllBooks(){
        return bookRepository.findAll().stream().map(book -> mapper.map(book, ResponseBookDTO.class)).collect(Collectors.toList());
    }

    public ResponseBookDTO getBook(int id){
        return bookRepository.findById(id)
                .map(book -> mapper.map(book, ResponseBookDTO.class)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "book not found"));
    }

    public void deleteBook(int id){
        if (bookRepository.existsById(id)){
            bookRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "book not found");
        }
    }

    public GenreBooksDTO getGenreBooks(int id){
       try {
           Genre genre = genreRepository.findById(id).get();
           Set<Book> books = genre.getBooks();
           Set<ResponseBookDTO> booksToSet = new HashSet<>();
           for (Book book : books) {
               booksToSet.add(mapper.map(book, ResponseBookDTO.class));
           }
           GenreBooksDTO genreBooksDTO = mapper.map(genre, GenreBooksDTO.class);
           genreBooksDTO.setGenreBooks(booksToSet);
           return genreBooksDTO;
       } catch (NoSuchElementException e){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "genre not found");
       }
    }

    public CreateBookDTO updateBook(int id, CreateBookDTO createBookDTO){
        try {
            Book book = bookRepository.findById(id).get();
            book.setTitle(createBookDTO.getTitle());
            book.setPages(createBookDTO.getPages());
            book.setPublished(createBookDTO.getPublished());
            book.setAuthor(mapper.map(createBookDTO.getAuthor(), Author.class));
            Set<Genre> genresToSet = new HashSet<>();
            for (GenreDTO g: createBookDTO.getGenres()){
                genresToSet.add(mapper.map(g, Genre.class));
            }
            book.setGenres(genresToSet);
            bookRepository.save(book);

            return mapper.map(book, CreateBookDTO.class);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "book not found");
        }
    }
}
