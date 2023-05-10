package com.project.pc.controller;

import com.project.pc.model.Mentor;
import com.project.pc.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/api/mentors")
public class MentorController {
    @Autowired
    private MentorService mentorService;
    @PostMapping
    public ResponseEntity<Mentor> createMentor(@RequestBody Mentor mentor){
        Mentor createdMentor = mentorService.createMentor(mentor);
        return new ResponseEntity<>(createdMentor, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Mentor>> getAllMentors(){
        List<Mentor> mentors = mentorService.getAllMentors();
        return new ResponseEntity<>(mentors, HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<Mentor> getMentorById(@PathVariable("id") Long id){
        Mentor mentor = mentorService.getMentorById(id);
        if (mentor == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(mentor, HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<Mentor> updateMentor(@PathVariable("id") Long id, @RequestBody Mentor mentor){
        Mentor update = mentorService.updateMentor(id, mentor);
        if (update == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(update, HttpStatus.OK);
    }
}
