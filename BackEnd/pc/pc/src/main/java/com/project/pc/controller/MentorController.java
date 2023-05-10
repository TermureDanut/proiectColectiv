package com.project.pc.controller;

import com.project.pc.model.Mentor;
import com.project.pc.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
