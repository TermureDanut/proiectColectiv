package com.project.pc.repository;

import com.project.pc.model.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface MentorRepository extends JpaRepository<Mentor, Long> {
    Optional<Mentor> findMentorById(long id);
    List<Mentor> findMentorByName(String name);
    Optional<Mentor> findMentorByEmail(String email);
}
