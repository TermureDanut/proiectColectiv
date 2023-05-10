package com.project.pc.controller;

import com.project.pc.model.Task;
import com.project.pc.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        return new ResponseEntity<>(taskService.createTask(task), HttpStatus.CREATED);
    }
}
