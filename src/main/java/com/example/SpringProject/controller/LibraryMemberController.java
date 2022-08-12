package com.example.SpringProject.controller;

import com.example.SpringProject.dto.CreateBookDTO;
import com.example.SpringProject.dto.CreateReadingListDTO;
import com.example.SpringProject.dto.CreateUserDTO;
import com.example.SpringProject.dto.ReadingListDTO;
import com.example.SpringProject.model.ReadingList;
import com.example.SpringProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class LibraryMemberController {

    @Autowired
    private UserService userService;

    @PostMapping
    public CreateUserDTO createUser(@Valid @RequestBody CreateUserDTO createUserDTO){
        return userService.create(createUserDTO);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        userService.delete(id);
    }

    @GetMapping("/{id}/reading_lists")
    public Set<ReadingListDTO> getUserLists(@PathVariable int id){
        return userService.getLists(id);
    }

    @PostMapping("/{id}/reading_lists")
    public ReadingListDTO createList(@PathVariable int id, CreateReadingListDTO createReadingListDTO){
        return userService.createListForUser(id, createReadingListDTO);
    }

    @GetMapping("/{id}/reading_lists/{list_id}")
    public ReadingListDTO getList(@PathVariable int id, @PathVariable int list_id){
        return userService.getList(id, list_id);
    }
}
