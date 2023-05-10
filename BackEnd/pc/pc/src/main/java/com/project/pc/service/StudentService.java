package com.project.pc.service;

import com.project.pc.model.Student;
import com.project.pc.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public Student createStudent(Student student){
        return studentRepository.save(new Student(student.getName(), student.getEmail()));
    }
    public List<Student> getAllStudents(){
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
        return students;
    }
    public Student getStudentById (Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null){
            return null;
        }
        return student;
    }
    public Student updateAllFieldsOfStudent (Long id, Student newStudent){
        Student student = studentRepository.findStudentById(id).orElse(null);
        if (student == null){
            return null;
        }
        student.setName(newStudent.getName());
        student.setEmail(newStudent.getEmail());
        studentRepository.save(student);
        return student;
    }
    public Student updateNeededFieldOfStudent(long id, Student updatedStudent) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null){
            return null;
        }
        if (updatedStudent.getName() != null) {
            student.setName(updatedStudent.getName());
        }
        if (updatedStudent.getEmail() != null) {
            student.setEmail(updatedStudent.getEmail());
        }
        studentRepository.save(student);
        return student;
    }
    public HttpStatus deleteAllStudents(){
        studentRepository.deleteAll();
        return HttpStatus.OK;
    }
    public HttpStatus deleteStudentById(long id){
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()){
            studentRepository.deleteById(id);
            return HttpStatus.OK;
        }else {
            return HttpStatus.NOT_FOUND;
        }
    }
}
