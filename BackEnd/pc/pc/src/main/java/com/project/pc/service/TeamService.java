package com.project.pc.service;

import com.project.pc.model.Team;
import com.project.pc.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;
    public Team createTeam(Team team){
        return teamRepository.save(new Team(team.getTeamLeader()));
    }
    public List<Team> getAllTeams(){
        List<Team> teams = teamRepository.findAll();
        return teams;
    }
    public Team getTeamById(Long id){
        return teamRepository.findById(id).orElse(null);
    }
    public Team updateTeam(Long id, Team team){
        Team update = teamRepository.findById(id).orElse(null);
        if (update == null){
            return null;
        }
        update.setTeamLeader(team.getTeamLeader());
        teamRepository.save(update);
        return update;
    }
    public HttpStatus deleteAllTeams(){
        teamRepository.deleteAll();
        return HttpStatus.OK;
    }
    public HttpStatus deleteTeamById(Long id){
        Optional<Team> team = teamRepository.findById(id);
        if (team.isPresent()){
            teamRepository.deleteById(id);
            return HttpStatus.OK;
        }else {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
