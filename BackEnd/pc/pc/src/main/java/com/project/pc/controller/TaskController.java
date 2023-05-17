package com.project.pc.controller;

import com.project.pc.model.Task;
import com.project.pc.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") Long id){
        Task task = taskService.getTaskById(id);
        if (task == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);
    }
    @GetMapping("{id}/attendance")
    public ResponseEntity<Integer> getAttendance(@PathVariable("id") Long id) {
        Task task = taskService.getTaskById(id);
        if (task == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(task.getAttendance(), HttpStatus.OK);
    }
    @GetMapping("{id}/grade")
    public ResponseEntity<Integer> getGrade(@PathVariable("id") Long id) {
        Task task = taskService.getTaskById(id);
        if (task == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(task.getGrade(), HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long id, @RequestBody Task task){
        Task update = taskService.updateTask(id, task);
        if (update == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(update, HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Task> patchTask(@PathVariable("id") Long id, @RequestBody Task task) {
        Task updated = taskService.patchTask(id, task);
        if (updated == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllTasks(){
        return new ResponseEntity<>(taskService.deleteAllTasks());
    }
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteTaskById(@PathVariable("id") Long id){
        return new ResponseEntity<>(taskService.deleteTaskById(id));
    }
}
