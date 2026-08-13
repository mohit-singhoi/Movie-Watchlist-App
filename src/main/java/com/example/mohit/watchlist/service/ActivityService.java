package com.example.mohit.watchlist.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.mohit.watchlist.entity.User;
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

    
    public List<Activity> getActivitiesByUser(User user) {
        return activityRepo.findByUserOrderByCreatedAtDesc(user);
    }
    
 // Delete all activities of a specific user
    public void deleteActivitiesByUser(User user) {
        activityRepo.deleteAll(activityRepo.findByUserOrderByCreatedAtDesc(user));
    }
    
    public void deleteActivityByIdForUser(Long activityId, Long userId) {

        Activity activity = activityRepo.findById(activityId)
                .orElse(null);

        if (activity == null) {
            throw new RuntimeException("Activity not found");
        }

        if (activity.getUser() == null ||
            !activity.getUser().getId().equals(userId)) {

            throw new RuntimeException(
                "Activity does not belong to this user"
            );
        }

        activityRepo.delete(activity);
    }
}