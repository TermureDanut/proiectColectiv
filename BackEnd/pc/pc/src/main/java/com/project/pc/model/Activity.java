package com.project.pc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table
public class Activity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    @NotBlank
    private String name;
    @Column
    @NotBlank
    private String description;
    @Column
    @NotBlank
    private String deadline;
    @OneToOne
    @JoinColumn
    private Status status;
    public Activity() {
        this.name = this.description = this.deadline = "";
    }
    public Activity(String name, String description, String deadline) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDeadline() {return deadline;}
    public void setDeadline(String deadline) {this.deadline = deadline;}
    public Status getStatus(){return status;}
    public void setStatus(Status status){this.status = status;}
}
