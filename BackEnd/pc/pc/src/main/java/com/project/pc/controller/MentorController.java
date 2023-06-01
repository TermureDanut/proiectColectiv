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
@RequestMapping("/api/mentors/")
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
    @GetMapping("name/{name}")
    public ResponseEntity<List<Mentor>> getMentorByName(@PathVariable("name") String name){
        List<Mentor> mentors = mentorService.getMentorByName(name);
        return new ResponseEntity<>(mentors, HttpStatus.FOUND);
    }
    @GetMapping("email/{email}")
    public ResponseEntity<Mentor> getMentorByEmail(@PathVariable("email") String email){
        Mentor mentor = mentorService.getMentorByEmail(email);
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
    @PatchMapping("{id}")
    public ResponseEntity<Mentor> patchMentor(@PathVariable("id") Long id, @RequestBody Mentor mentor){
        Mentor update = mentorService.patchMentor(id, mentor);
        if (update == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(update, HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllMentors(){
        return new ResponseEntity<>(mentorService.deleteAllMentors());
    }
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteMentorById(@PathVariable("id") Long id){
        return new ResponseEntity<>(mentorService.deleteMentorById(id));
    }
}
