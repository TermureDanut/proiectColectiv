package com.project.pc.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ActivityDTO {
    @Column(unique = true)
    private String name;
    private String description;
    private String deadline;
}
