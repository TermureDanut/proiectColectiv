package com.project.pc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Nullable
    private Activity activity;
    @ManyToMany(fetch = FetchType.LAZY,  mappedBy = "tasks")
    @JsonIgnore
    private Set<Mentor> mentors = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY,  mappedBy = "tasks")
    @JsonIgnore
    private Set<Student> students = new HashSet<>();
    public Task(){
        this.grade = this.attendance = 0;
        this.description = this.deadline = "";
    }
    public Task(String description, String deadline) {
        this.grade = 0;
        this.description = description;
        this.deadline = deadline;
        this.attendance = 0;
    }
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}
    public int getGrade() {return grade;}
    public void setGrade(int grade) {this.grade = grade;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public String getDeadline() {return deadline;}
    public void setDeadline(String deadline) {this.deadline = deadline;}
    public int getAttendance() {return attendance;}
    public void setAttendance(int attendance) {this.attendance = attendance;}
    public Activity getActivity() {return activity;}
    public void setActivity(Activity activity) {this.activity = activity;}
    public Set<Mentor> getMentors() {return mentors;}
    public void setMentors(Set<Mentor> mentors) {this.mentors = mentors;}
    public void addMentorToMentors(Mentor mentor){this.mentors.add(mentor);}
    public Set<Student> getStudents() {return students;}
    public void setStudents(Set<Student> students) {this.students = students;}
    public void addStudentToStudent(Student student) {this.students.add(student);}
}
