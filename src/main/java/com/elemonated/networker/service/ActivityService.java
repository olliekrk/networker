package com.elemonated.networker.service;

import com.elemonated.networker.model.presence.PresenceData;
import com.elemonated.networker.persistence.data.Activity;
import com.elemonated.networker.persistence.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public PresenceData getPresenceDataForEmployee(Long employeeId) {
        List<Activity> employeeActivities = activityRepository.findByEmployee_id(employeeId);
        // todo: map list of activities to PresenceData
        return null;
    }
}
