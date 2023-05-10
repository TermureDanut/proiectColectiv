package com.project.pc.service;

import com.project.pc.model.Activity;
import com.project.pc.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;
    public Activity createActivity(Activity activity){
        return activityRepository.save(new Activity(activity.getName(), activity.getDescription()));
    }
}
