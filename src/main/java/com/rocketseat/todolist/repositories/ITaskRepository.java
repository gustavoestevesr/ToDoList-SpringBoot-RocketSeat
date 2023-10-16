package com.rocketseat.todolist.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rocketseat.todolist.models.TaskModel;
import java.util.List;


public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {
    
    List<TaskModel> findByTitle(String title);
    
}
