package com.rocketseat.todolist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.todolist.models.TaskModel;
import com.rocketseat.todolist.repositories.ITaskRepository;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    
    @Autowired
    private ITaskRepository taskRepository;

    @PostMapping
    ResponseEntity createTask(@RequestBody TaskModel taskModel) {

        // Verifica se já existe o 'title'
        var taskFound = this.taskRepository.findByTitle(taskModel.getTitle());
        if(taskFound != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The user already exists");
        }

        TaskModel taskSaved = taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskSaved);
    }
}
