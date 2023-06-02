package com.project.pc.service;

import com.project.pc.dto.ActivityDTO;
import com.project.pc.model.Activity;
import org.springframework.stereotype.Service;

@Service
public class MappingService {
    public ActivityDTO convertActivityIntoDTO(Activity activity){
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setName(activity.getName());
        activityDTO.setDescription(activity.getDescription());
        activityDTO.setDeadline(activity.getDeadline());
        return activityDTO;
    }
}
