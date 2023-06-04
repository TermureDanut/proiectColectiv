package com.project.pc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    @NotBlank
    private String name;
    @Column(unique = true)
    @Email
    @NotBlank
    private String email;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "mentor_task", joinColumns = { @JoinColumn(name = "mentor_id") }, inverseJoinColumns = { @JoinColumn(name = "task_id") })
    private Set<Task> tasks = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "mentor_team", joinColumns = { @JoinColumn(name = "mentor_id") }, inverseJoinColumns = { @JoinColumn(name = "team_id") })
    private Set<Team> teams = new HashSet<>();
    @OneToOne
    @JoinColumn
    private Status status;
    public Mentor(){
        this.name = this.email = "";
    }
    public Mentor(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Set<Task> getTasks(){return this.tasks;}
    public void setTasks(Set<Task> tasks){this.tasks = tasks;}
    public void addTaskToTasks(Task task){this.tasks.add(task);}
    public Set<Team> getTeams() {return teams;}
    public void setTeams(Set<Team> teams) {this.teams = teams;}
    public void addTeamToTeams(Team team){this.teams.add(team);}
    public Status getStatus(){return status;}
    public void setStatus(Status status){this.status = status;}
}
