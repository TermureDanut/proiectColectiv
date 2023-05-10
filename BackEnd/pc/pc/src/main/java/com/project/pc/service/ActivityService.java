package com.project.pc.service;

import com.project.pc.model.Activity;
import com.project.pc.model.Student;
import com.project.pc.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;
    public Activity createActivity(Activity activity){
        return activityRepository.save(new Activity(activity.getName(), activity.getDescription()));
    }
    public List<Activity> getAllActivities(){
        List<Activity> activities = new ArrayList<>();
        activityRepository.findAll().forEach(activities::add);
        return activities;
    }
    public Activity getActivityById (Long id) {
        Activity activity = activityRepository.findById(id).orElse(null);
        if (activity == null){
            return null;
        }
        return activity;
    }
    public Activity updateAllFieldsOfActivity (Long id, Activity newActivity){
        Activity activity = activityRepository.findById(id).orElse(null);
        if (activity == null){
            return null;
        }
        activity.setName(newActivity.getName());
        activity.setDescription(newActivity.getDescription());
        activityRepository.save(activity);
        return activity;
    }
}
