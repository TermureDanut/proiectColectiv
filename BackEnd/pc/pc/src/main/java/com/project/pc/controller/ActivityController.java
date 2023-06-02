package com.project.pc.controller;

import com.project.pc.model.Activity;
import com.project.pc.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/activities/")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @PostMapping
    public ResponseEntity<String> createActivity(@RequestBody Activity activity) {
        return activityService.createActivity(activity);
    }
    @GetMapping
    public ResponseEntity<List<Activity>> getAllActivities(){
        List<Activity> activities = activityService.getAllActivities();
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable("id") Long id) {
        Activity activity = activityService.getActivityById(id);
        if (activity == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(activity, HttpStatus.FOUND);
    }
    @GetMapping("name/{name}")
    public ResponseEntity<Optional<Activity>> getActivityByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(activityService.getActivityByName(name), HttpStatus.FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable("id") Long id, @RequestBody Activity activity){
        Activity updated = activityService.updateActivity(id, activity);
        if (updated == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Activity> patchActivity(@PathVariable("id") Long id, @RequestBody Activity activity) {
        Activity updated = activityService.patchActivity(id, activity);
        if (updated == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
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
