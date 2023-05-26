package com.project.pc.service;

import com.project.pc.model.Student;
import com.project.pc.model.Team;
import com.project.pc.repository.StudentRepository;
import com.project.pc.repository.TeamRepository;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeamRepository teamRepository;
    private Validator validator;
    public StudentService() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    public ResponseEntity<String> createStudent(Student student) {
        Set<ConstraintViolation<Student>> violations = validator.validate(student);
        if (!violations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Student email");
        }
        try {
            studentRepository.save(student);
            return ResponseEntity.status(HttpStatus.CREATED).body("Student created successfully");
        } catch (DataIntegrityViolationException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate entry: Student already exists");
        }
    }
    public Student addToTeam(Long id, Long tId){
        Student student = studentRepository.findById(id).orElse(null);
        Team team = teamRepository.findById(tId).orElse(null);
        if (student == null || team == null){
            return null;
        }
        student.setTeam(team);
        studentRepository.save(student);
        return student;
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
    public List<Student> getStudentByName(String name){
        return studentRepository.findStudentByName(name);
    }
    public Student getStudentByEmail(String email){
        return studentRepository.findStudentByEmail(email).orElse(null);
    }
    public List<Student> getTeamMembers(Long tId){
        return studentRepository.findByTeamId(tId);
    }
    public Student updateStudent (Long id, Student student){
        Student update = studentRepository.findStudentById(id).orElse(null);
        if (update == null){
            return null;
        }
        update.setName(student.getName());
        update.setEmail(student.getEmail());
        studentRepository.save(update);
        return update;
    }
    public Student patchStudent(long id, Student student) {
        Student update = studentRepository.findById(id).orElse(null);
        if (update == null){
            return null;
        }
        if (student.getName() != null) {
            update.setName(student.getName());
        }
        if (student.getEmail() != null) {
            update.setEmail(student.getEmail());
        }
        studentRepository.save(update);
        return update;
    }
    public HttpStatus deleteFromTeam(Long id, Long tId){
        Student student = studentRepository.findStudentById(id).orElse(null);
        if (student == null ||student.getTeam() == null || student.getTeam().getId() != tId){
            return HttpStatus.BAD_REQUEST;
        }
        student.setTeam(null);
        studentRepository.save(student);
        return HttpStatus.OK;
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
            return HttpStatus.BAD_REQUEST;
        }
    }
}
