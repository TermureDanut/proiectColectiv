package com.project.pc.model;

import jakarta.persistence.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
@Entity
@Table
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String createdBy;
    @Column
    private String creationDate;
    @Column
    private String modifiedBy;
    @Column
    private String modificationDate;
    @OneToOne(mappedBy = "status")
    private Activity activity;
    @OneToOne(mappedBy = "status")
    private Mentor mentor;
    @OneToOne(mappedBy = "status")
    private Student student;
    @OneToOne(mappedBy = "status")
    private Task task;
    @OneToOne(mappedBy = "status")
    private Team team;
    public Status() {
        this.createdBy = "ADMIN";
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        this.creationDate = dateFormat.format(date);
        this.modifiedBy = this.modificationDate = "-";
    }
    public long getId() {return id;}
    public String getCreatedBy() {return createdBy;}
    public String getCreationDate() {return creationDate;}
    public String getModifiedBy() {return modifiedBy;}
    public void setModifiedBy() {this.modifiedBy = "ADMIN";}
    public String getModificationDate() {return modificationDate;}
    public void setModificationDate() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        this.modificationDate = dateFormat.format(date);
    }
}
