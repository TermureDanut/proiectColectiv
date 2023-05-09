package com.project.pc.service;

import com.project.pc.model.Student;
import com.project.pc.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public HttpStatus createStudent(Student student){
        Student student1 = new Student();
        if (Objects.nonNull(student.getEmail())){
            student1.setEmail(student.getEmail());
        }else{
            return HttpStatus.BAD_REQUEST;
        }
        if (Objects.nonNull(student.getName())){
            student1.setName(student.getName());
        }else{
            return HttpStatus.BAD_REQUEST;
        }
        studentRepository.save(student1);
        return HttpStatus.CREATED;
    }
    public List<Student> getAllStudents(){
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
        return students;
    }
    public Student getStudentById (Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(null);
    }
}
