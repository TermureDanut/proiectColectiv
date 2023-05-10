package com.project.pc.controller;

import com.project.pc.model.Student;
import com.project.pc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student createdStudent = studentService.createStudent(student);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
        Student student = studentService.getStudentById(id);
        if (student == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(student, HttpStatus.FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id, @RequestBody Student student){
        Student update = studentService.updateStudent(id, student);
        if (update == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(update, HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Student> patchStudent(@PathVariable("id") Long id, @RequestBody Student student) {
        Student updated = studentService.patchStudent(id, student);
        if (updated == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllStudents(){
        return new ResponseEntity<>(studentService.deleteAllStudents());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStudentById(@PathVariable("id") Long id){
        return new ResponseEntity<>(studentService.deleteStudentById(id));
    }
}
