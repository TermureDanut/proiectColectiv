package com.project.pc.service;

import com.project.pc.model.Mentor;
import com.project.pc.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MentorService {
    @Autowired
    private MentorRepository mentorRepository;
    public Mentor createMentor(Mentor mentor){
        return mentorRepository.save(new Mentor(mentor.getName(), mentor.getEmail()));
    }
}
