package com.project.pc.service;

import com.project.pc.dto.ActivityDTO;
import com.project.pc.model.Activity;
import com.project.pc.model.Status;
import com.project.pc.model.Task;
import com.project.pc.repository.ActivityRepository;
import com.project.pc.repository.StatusRepository;
import com.project.pc.repository.TaskRepository;
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
    private TaskRepository taskRepository;
    @Autowired
    private MappingService mappingService;
    private final Validator validator;
    public ActivityService() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    public ResponseEntity<String> createActivity(Activity activity){
        Set<ConstraintViolation<Activity>> violations = validator.validate(activity);
        if (!violations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fields can't be empty!");
        }
        try {
            Status status = new Status();
            statusRepository.save(status);
            activity.setStatus(status);
            activityRepository.save(activity);
            return ResponseEntity.status(HttpStatus.CREATED).body("Activity created successfully!");
        } catch (DataIntegrityViolationException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate entry: activity  : " + activity.getName() + ", already exists!");
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
    public ActivityDTO getActivityById (Long id) {
        Activity activity = activityRepository.findById(id).orElse(null);
        if (activity == null){
            return null;
        }
        return mappingService.convertActivityIntoDTO(activity);
    }
    public ActivityDTO getActivityByName(String name){
        Activity activity = activityRepository.findByName(name).orElse(null);
        if (activity == null){
            return null;
        }
        return mappingService.convertActivityIntoDTO(activity);
    }
    public ResponseEntity<String> updateActivity (Long id, Activity activity){
        Set<ConstraintViolation<Activity>> violations = validator.validate(activity);
        if (!violations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fields can't be empty!");
        }
        try {
            Activity update = activityRepository.findById(id).orElse(null);
            if (update == null) {
                return null;
            }
            Status status = statusRepository.findById(update.getStatus().getId()).orElse(null);
            if (status == null) {
                return null;
            }
            status.setModifiedBy();
            status.setModificationDate();
            statusRepository.save(status);
            update.setName(activity.getName());
            update.setDescription(activity.getDescription());
            update.setDeadline(activity.getDeadline());
            update.setStatus(status);
            activityRepository.save(update);
            return ResponseEntity.status(HttpStatus.OK).body("Activity updated successfully!");
        } catch (DataIntegrityViolationException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate entry: activity : " + activity.getName() + ", already exists!");
        }
    }
    // TODO validate only fields that i need to be patched
    public ResponseEntity<String> patchActivity(long id, Activity activity) {
        Set<ConstraintViolation<Activity>> violations = validator.validate(activity);
        if (!violations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fields can't be empty!");
        }
        try {
            Activity update = activityRepository.findById(id).orElse(null);
            if (update == null) {
                return null;
            }
            Status status = statusRepository.findById(update.getStatus().getId()).orElse(null);
            if (status == null) {
                return null;
            }
            status.setModifiedBy();
            status.setModificationDate();
            statusRepository.save(status);
            if (activity.getName() != null) {
                update.setName(activity.getName());
            }else{
                update.setName(update.getName());
            }
            if (activity.getDescription() != null) {
                update.setDescription(activity.getDescription());
            }else{
                update.setDescription(update.getDescription());
            }
            if (activity.getDeadline() != null){
                update.setDeadline(activity.getDeadline());
            }else{
                update.setDeadline(update.getDeadline());
            }
            update.setStatus(status);
            activityRepository.save(update);
            return ResponseEntity.status(HttpStatus.OK).body("Activity patched successfully!");
        } catch (DataIntegrityViolationException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate entry: activity : " + activity.getName() + ", already exists!");
        }
    }
    public HttpStatus deleteAllActivities(){
        List<Activity> activities = activityRepository.findAll();
        for (Activity activity : activities){
            setNullToTasks(activity);
        }
        activityRepository.deleteAll();
        return HttpStatus.OK;
    }
    public HttpStatus deleteActivityById(long id){
        Activity activity = activityRepository.findById(id).orElse(null);
        if (activity != null){
            setNullToTasks(activity);
            activityRepository.deleteById(id);
            return HttpStatus.OK;
        }else {
            return HttpStatus.BAD_REQUEST;
        }
    }
    public HttpStatus deleteActivityByName(String name){
        Activity activity = activityRepository.findByName(name).orElse(null);
        if (activity != null){
            setNullToTasks(activity);
            activityRepository.deleteById(activity.getId());
            return HttpStatus.OK;
        }else {
            return HttpStatus.BAD_REQUEST;
        }
    }
    public void setNullToTasks(Activity activity){
        List<Task> tasks = taskRepository.findTasksByActivity_Id(activity.getId());
        for (Task task : tasks){
            task.setActivity(null);
            taskRepository.save(task);
        }
    }
}
