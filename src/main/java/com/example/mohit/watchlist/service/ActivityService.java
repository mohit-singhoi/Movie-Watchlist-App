package com.example.mohit.watchlist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mohit.watchlist.entity.Activity;
import com.example.mohit.watchlist.repository.ActivityRepo;

@Service
public class ActivityService {

    private final ActivityRepo activityRepo;

    public ActivityService(ActivityRepo activityRepo) {
        this.activityRepo = activityRepo;
    }

    public Activity saveActivity(Activity activity) {
        return activityRepo.save(activity);
    }

    public List<Activity> getAllActivities() {
        return activityRepo.findAllByOrderByCreatedAtDesc();
    }

    public Activity getActivityById(Long id) {
        return activityRepo.findById(id).orElse(null);
    }

    public long getTotalActivities() {
        return activityRepo.count();
    }

}