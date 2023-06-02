package com.project.pc.service;

import com.project.pc.model.Activity;
import com.project.pc.model.Mentor;
import com.project.pc.model.Team;
import com.project.pc.repository.ActivityRepository;
import com.project.pc.repository.MentorRepository;
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
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private MentorRepository mentorRepository;
    public Team createTeam(Team team){
        return teamRepository.save(new Team(team.getTeamLeader(), team.getTeamName()));
    }
    public Team addToActivity(Long id, Long aId){
        Team team = teamRepository.findById(id).orElse(null);
        Activity activity = activityRepository.findById(aId).orElse(null);
        if (team == null || activity == null){
            return null;
        }
        team.setActivity(activity);
        teamRepository.save(team);
        return team;
    }
    public Team addToMentor(Long id, Long mId){
        Team team = teamRepository.findById(id).orElse(null);
        Mentor mentor = mentorRepository.findById(mId).orElse(null);
        if (team == null || mentor == null){
            return null;
        }
        teamRepository.save(team);
        return team;
    }
    public List<Team> getAllTeams(){
        List<Team> teams = teamRepository.findAll();
        return teams;
    }
    public Team getTeamById(Long id){
        return teamRepository.findById(id).orElse(null);
    }
    public Team getTeamByTeamLeader(Long id){
        return teamRepository.findByTeamLeader(id).orElse(null);
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
