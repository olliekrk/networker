package com.elemonated.networker.rest;

import com.elemonated.networker.model.presence.PresenceData;
import com.elemonated.networker.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activity")
public class ActivityRest {

    private final ActivityService activityService;

    @Autowired
    public ActivityRest(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/presence/{employeeId}")
    public PresenceData getPresenceDataForEmployee(@PathVariable Long employeeId) {
        return activityService.getPresenceDataForEmployee(employeeId);
    }
}
