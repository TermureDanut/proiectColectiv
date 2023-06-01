package com.project.pc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
@Table
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String name;
    @Column(unique = true)
    @Email
    private String email;
    public Mentor(){}
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
}
