package com.project.pc.model;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private int grade;
    @Column
    private String description;
    @Column
    private String deadline;
    @Column
    private int attendance;
    public Task(){}
    public Task(int grade, String description, String deadline, int attendance) {
        this.grade = grade;
        this.description = description;
        this.deadline = deadline;
        this.attendance = attendance;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getGrade() {
        return grade;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDeadline() {
        return deadline;
    }
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
    public int getAttendance() {
        return attendance;
    }
    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }
}
