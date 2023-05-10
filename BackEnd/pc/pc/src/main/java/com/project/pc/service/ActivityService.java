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
    public Activity updateActivity (Long id, Activity activity){
        Activity update = activityRepository.findById(id).orElse(null);
        if (update == null){
            return null;
        }
        update.setName(activity.getName());
        update.setDescription(activity.getDescription());
        activityRepository.save(update);
        return update;
    }
    public Activity patchActivity(long id, Activity activity) {
        Activity update = activityRepository.findById(id).orElse(null);
        if (update == null){
            return null;
        }
        if (activity.getName() != null) {
            update.setName(activity.getName());
        }
        if (activity.getDescription() != null) {
            update.setDescription(activity.getDescription());
        }
        activityRepository.save(update);
        return update;
    }
}
