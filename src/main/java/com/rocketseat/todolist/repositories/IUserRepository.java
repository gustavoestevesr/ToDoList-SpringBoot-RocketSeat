package com.rocketseat.todolist.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rocketseat.todolist.models.UserModel;
import java.util.List;


public interface IUserRepository extends JpaRepository <UserModel, UUID> {

    List<UserModel> findByUsername(String username);
    List<UserModel> findByName(String name);

}
