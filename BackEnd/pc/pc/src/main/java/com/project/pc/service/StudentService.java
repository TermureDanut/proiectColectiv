package com.project.pc.service;

import com.project.pc.model.Student;
import com.project.pc.model.Team;
import com.project.pc.repository.StudentRepository;
import com.project.pc.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeamRepository teamRepository;
    public Student createStudent(Student student){
        return studentRepository.save(new Student(student.getName(), student.getEmail()));
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
