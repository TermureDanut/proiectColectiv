package com.project.pc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Student{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String name;
    @Column(unique = true)
    @Email
    private String email;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Nullable
    private Team team;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "student_task", joinColumns = { @JoinColumn(name = "student_id") }, inverseJoinColumns = { @JoinColumn(name = "task_id") })
    private Set<Task> tasks = new HashSet<>();
    @OneToOne
    @JoinColumn
    private Status status;
    public Student() {
        this.name = this.email = "";
    }
    public Student(String name, String email){
        this.name = name;
        this.email = email;
    }
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public Team getTeam() {return team;}
    public void setTeam(Team team) {this.team = team;}
    public Set<Task> getTasks() {return tasks;}
    public void setTasks(Set<Task> tasks) {this.tasks = tasks;}
    public void addTaskToTasks(Task task){this.tasks.add(task);}
    public Status getStatus(){return status;}
    public void setStatus(Status status){this.status = status;}
}
