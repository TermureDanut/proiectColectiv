package com.project.pc.model;

import jakarta.persistence.*;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private long teamLeader;
    public Team() {}
    public Team(long teamLeader) {
        this.teamLeader = teamLeader;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getTeamLeader() {
        return teamLeader;
    }
    public void setTeamLeader(long teamLeader) {
        this.teamLeader = teamLeader;
    }
}
