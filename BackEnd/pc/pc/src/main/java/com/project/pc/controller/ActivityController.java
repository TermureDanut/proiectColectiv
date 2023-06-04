package com.project.pc.controller;

import com.project.pc.dto.ActivityDTO;
import com.project.pc.model.Activity;
import com.project.pc.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/activities/")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @PostMapping
    public ResponseEntity<String> createActivity(@RequestBody Activity activity){
        return activityService.createActivity(activity);
    }
    @GetMapping
    public ResponseEntity<List<ActivityDTO>> getAllActivities(){
        return new ResponseEntity<>(activityService.getAllActivities(), HttpStatus.OK);
    }
    @GetMapping("id/{id}")
    public ResponseEntity<?> getActivityById(@PathVariable("id") Long id){
        ActivityDTO activity = activityService.getActivityById(id);
        if (activity == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Activity with id : " + id + ", not found.");
        }
        return new ResponseEntity<>(activity, HttpStatus.FOUND);
    }
    @GetMapping("name/{name}")
    public ResponseEntity<?> getActivityByName(@PathVariable("name") String name){
        ActivityDTO activity = activityService.getActivityByName(name);
        if (activity == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Activity with name : " + name + ", not found.");
        }
        return new ResponseEntity<>(activity, HttpStatus.FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateActivity(@PathVariable("id") Long id, @RequestBody Activity activity){
        if (activityService.updateActivity(id, activity) == null){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Activity with id : " + id + ", not found.");
        }
        return activityService.updateActivity(id, activity);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<String> patchActivity(@PathVariable("id") Long id, @RequestBody Activity activity){
        if (activityService.patchActivity(id, activity) == null){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Activity with id : " + id + ", not found.");
        }
        return activityService.patchActivity(id, activity);
    }
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllActivities(){
        return new ResponseEntity<>(activityService.deleteAllActivities());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteActivityById(@PathVariable("id") Long id){
        return new ResponseEntity<>(activityService.deleteActivityById(id));
    }
}
