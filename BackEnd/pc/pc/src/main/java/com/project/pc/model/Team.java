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
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private long teamLeader;
    @Column(unique = true)
    private String teamName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Nullable
    private Activity activity;
    @ManyToMany(fetch = FetchType.LAZY,  mappedBy = "teams")
    @JsonIgnore
    private Set<Mentor> mentors = new HashSet<>();
    public Team() {
        this.teamLeader = 0;
        this.teamName = "";
    }
    public Team(long teamLeader, String teamName) {
        this.teamLeader = teamLeader;
        this.teamName = teamName;
    }
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}
    public long getTeamLeader() {return teamLeader;}
    public void setTeamLeader(long teamLeader) {this.teamLeader = teamLeader;}
    public String getTeamName() {return teamName;}
    public void setTeamName(String teamName) {this.teamName = teamName;}
    public Activity getActivity() {return activity;}
    public void setActivity(Activity activity) {this.activity = activity;}
    public Set<Mentor> getMentors() {return mentors;}
    public void setMentors(Set<Mentor> mentors) {this.mentors = mentors;}
    public void addMentorToMentors(Mentor mentor) {this.mentors.add(mentor);}
}