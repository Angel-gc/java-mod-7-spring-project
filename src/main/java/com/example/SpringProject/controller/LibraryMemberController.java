package com.example.SpringProject.controller;

import com.example.SpringProject.dto.CreateBookDTO;
import com.example.SpringProject.dto.CreateUserDTO;
import com.example.SpringProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class LibraryMemberController {

    @Autowired
    private UserService userService;

    @PostMapping
    public CreateUserDTO createUser(@Valid @RequestBody CreateUserDTO createUserDTO){
        return userService.create(createUserDTO);
    }


}
