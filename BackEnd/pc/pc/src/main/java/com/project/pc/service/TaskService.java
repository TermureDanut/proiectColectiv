package com.project.pc.service;

import com.project.pc.model.Task;
import com.project.pc.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    public Task createTask(Task task){
        return taskRepository.save(new Task(task.getGrade(), task.getDescription(), task.getDeadline(), task.getAttendance()));
    }
    public List<Task> getAllTasks(){
        List<Task> tasks = taskRepository.findAll();
        return tasks;
    }
    public Task getTaskById(Long id){
        return taskRepository.findById(id).orElse(null);
    }
}
