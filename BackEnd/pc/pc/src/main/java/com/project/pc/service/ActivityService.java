package com.project.pc.service;

import com.project.pc.dto.ActivityDTO;
import com.project.pc.model.Activity;
import com.project.pc.model.Status;
import com.project.pc.repository.ActivityRepository;
import com.project.pc.repository.StatusRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private MappingService mappingService;
    private Validator validator;
    public ActivityService() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    public ResponseEntity<String> createActivity(Activity activity){

        Set<ConstraintViolation<Activity>> violations = validator.validate(activity);
        if (!violations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid!");
        }
        try {
            Status status = new Status();
            statusRepository.save(status);
            activity.setStatus(status);
            activityRepository.save(activity);
            return ResponseEntity.status(HttpStatus.CREATED).body("Activity created successfully");
        } catch (DataIntegrityViolationException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate entry: activity already exists");
        }
    }
    public List<ActivityDTO> getAllActivities(){
        List<ActivityDTO> activityDTOS = new ArrayList<>();
        List<Activity> activities = activityRepository.findAll();
        for (Activity activity : activities){
            activityDTOS.add(mappingService.convertActivityIntoDTO(activity));
        }
        return activityDTOS;
    }
    public Activity getActivityById (Long id) {
        Activity activity = activityRepository.findById(id).orElse(null);
        if (activity == null){
            return null;
        }
        return activity;
    }
    public Optional<Activity> getActivityByName(String name){
        return activityRepository.findByName(name);
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
    public HttpStatus deleteAllActivities(){
        activityRepository.deleteAll();
        return HttpStatus.OK;
    }
    public HttpStatus deleteActivityById(long id){
        Optional<Activity> activity = activityRepository.findById(id);
        if (activity.isPresent()){
            activityRepository.deleteById(id);
            return HttpStatus.OK;
        }else {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
