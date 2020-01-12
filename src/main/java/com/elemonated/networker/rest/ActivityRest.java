package com.elemonated.networker.rest;

import com.elemonated.networker.model.ActivityDTO;
import com.elemonated.networker.model.presence.PresenceData;
import com.elemonated.networker.persistence.data.Activity;
import com.elemonated.networker.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activity")
public class ActivityRest {

    private final ActivityService activityService;

    @Autowired
    public ActivityRest(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping
    public Activity createActivity(@RequestBody ActivityDTO activityDTO) {
        return activityService.saveActivity(activityDTO);
    }

    @GetMapping("/presence/{employeeId}")
    public PresenceData getPresenceDataForEmployee(@PathVariable Long employeeId) {
        return activityService.getPresenceDataForEmployee(employeeId);
    }
}
