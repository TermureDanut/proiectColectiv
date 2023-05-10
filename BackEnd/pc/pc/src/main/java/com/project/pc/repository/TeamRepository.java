package com.project.pc.repository;

import com.project.pc.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findTeamById(long id);
    Optional<Team> findTeamByTeamLeader(long id);
}
