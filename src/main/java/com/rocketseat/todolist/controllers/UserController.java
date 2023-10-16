package com.rocketseat.todolist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.todolist.models.UserModel;
import com.rocketseat.todolist.repositories.IUserRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @PostMapping
    ResponseEntity createUser(@RequestBody UserModel userModel) {

        // Verifica se já existe o 'username'
        var userFound = this.userRepository.findByUsername(userModel.getUsername());
        if(userFound != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The user already exists");
        }

        // Encriptografando senha
        var passwordHashred = BCrypt.withDefaults()
        .hashToString(12, userModel.getPassword().toCharArray());

        // Atribuindo a senha encriptografada
        userModel.setPassword(passwordHashred);

        UserModel userSaved = userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
    }
}
