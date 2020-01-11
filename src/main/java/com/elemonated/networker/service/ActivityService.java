package com.elemonated.networker.service;

import com.elemonated.networker.model.presence.PresenceDailyInfo;
import com.elemonated.networker.model.presence.PresenceData;
import com.elemonated.networker.model.presence.PresenceHourlyInfo;
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

        // mock
        PresenceData data = new PresenceData();
        PresenceDailyInfo mondayInfo = new PresenceDailyInfo();
        mondayInfo.setDayOfWeek("Monday");
        mondayInfo.setHourlyData(List.of(
                new PresenceHourlyInfo(0, 0.1),
                new PresenceHourlyInfo(1, 0.2),
                new PresenceHourlyInfo(2, 0.3),
                new PresenceHourlyInfo(3, 0.4),
                new PresenceHourlyInfo(4, 0.5),
                new PresenceHourlyInfo(5, 0.6),
                new PresenceHourlyInfo(6, 0.7)
        ));
        data.setDailyData(List.of(mondayInfo));
        return data;
    }
}
