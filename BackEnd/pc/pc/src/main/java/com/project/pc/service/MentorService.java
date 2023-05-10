package com.project.pc.service;

import com.project.pc.model.Mentor;
import com.project.pc.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MentorService {
    @Autowired
    private MentorRepository mentorRepository;
    public Mentor createMentor(Mentor mentor){
        return mentorRepository.save(new Mentor(mentor.getName(), mentor.getEmail()));
    }
    public List<Mentor> getAllMentors(){
        List<Mentor> mentors = mentorRepository.findAll();
        return mentors;
    }
    public Mentor getMentorById(Long id){
        return mentorRepository.findMentorById(id).orElse(null);
    }
    public Mentor updateMentor(Long id, Mentor mentor){
        Mentor update = mentorRepository.findMentorById(id).orElse(null);
        if (update == null){
            return null;
        }
        update.setName(mentor.getName());
        update.setEmail(mentor.getEmail());
        mentorRepository.save(update);
        return update;
    }
}
