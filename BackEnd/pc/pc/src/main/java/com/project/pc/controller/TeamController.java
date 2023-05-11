package com.project.pc.controller;

import com.project.pc.model.Team;
import com.project.pc.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/api/teams")
public class TeamController {
    @Autowired
    private TeamService teamService;
    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team){
        return new ResponseEntity<>(teamService.createTeam(team), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams(){
        return new ResponseEntity<>(teamService.getAllTeams(), HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable("id") Long id){
        Team team = teamService.getTeamById(id);
        if (team == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(team, HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable("id") Long id, @RequestBody Team team){
        Team update = teamService.updateTeam(id, team);
        if (update == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(update, HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllTeams(){
        return new ResponseEntity<>(teamService.deleteAllTeams());
    }
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteTeamById(@PathVariable("id") Long id){
        return new ResponseEntity<>(teamService.deleteTeamById(id));
    }
}
