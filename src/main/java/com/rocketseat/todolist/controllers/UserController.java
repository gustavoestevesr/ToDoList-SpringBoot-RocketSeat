package com.rocketseat.todolist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.todolist.models.UserModel;
import com.rocketseat.todolist.repositories.IUserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    ResponseEntity createUser(@RequestBody UserModel userModel) {

        var userFound = this.userRepository.findByUsername(userModel.getUsername());
        if(userFound != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The user already exists");
        }

        UserModel userSaved = userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
    }
}
