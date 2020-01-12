package com.elemonated.networker.service;

import com.elemonated.networker.model.ActivityDTO;
import com.elemonated.networker.model.presence.PresenceDailyInfo;
import com.elemonated.networker.model.presence.PresenceData;
import com.elemonated.networker.model.presence.PresenceHourlyInfo;
import com.elemonated.networker.persistence.data.Activity;
import com.elemonated.networker.persistence.repository.ActivityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.elemonated.networker.util.TimestampUtils.alignTimestampRangeToHours;
import static com.elemonated.networker.util.TimestampUtils.alignTimestampToHours;

@Service
public class ActivityService {

    private static final Logger logger = LoggerFactory.getLogger(ActivityService.class);

    private final ActivityRepository activityRepository;

    private final EmployeeService employeeService;

    @Autowired
    public ActivityService(ActivityRepository activityRepository, EmployeeService employeeService) {
        this.activityRepository = activityRepository;
        this.employeeService = employeeService;
    }

    private static List<Instant> getHourlyActivityGranulation(Activity activity) {
        Timestamp startTimestamp = activity.getStartTimestamp();
        Timestamp endTimestamp = activity.getEndTimestamp();

        if (startTimestamp == null) {
            return List.of();
        }

        if (endTimestamp == null) {
            return List.of(alignTimestampToHours(startTimestamp));
        }

        return alignTimestampRangeToHours(startTimestamp, endTimestamp);
    }

    public Activity saveActivity(ActivityDTO activityDTO) {
        Activity activity = new Activity();
        activity.setStartTimestamp(Timestamp.from(Instant.ofEpochMilli(activityDTO.getStartTimestamp())));
        activity.setEndTimestamp(Timestamp.from(Instant.ofEpochMilli(activityDTO.getEndTimestamp())));
        activity.setEmployee(employeeService.getEmployeeById(activityDTO.getEmployeeId()).orElse(null));

        return activityRepository.save(activity);
    }


    public Activity saveActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public PresenceData getPresenceDataForEmployee(Long employeeId) {
        List<Activity> employeeActivities = activityRepository.findByEmployee_id(employeeId);

        Set<Instant> allRecordedActivitiesData = employeeActivities.stream()
                .map(ActivityService::getHourlyActivityGranulation)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        PresenceData computedResult = computePresenceData(allRecordedActivitiesData);
        logger.debug("Computed activity data:" + computedResult);
        return computedResult;
    }

    private PresenceData computePresenceData(Collection<Instant> data) {
        return new PresenceData(
                Arrays.stream(DayOfWeek.values())
                        .map(day -> new PresenceDailyInfo(StringUtils.capitalize(day.name().toLowerCase()), IntStream.range(0, 24)
                                .mapToObj(hour -> new PresenceHourlyInfo(hour, (double) countByDayAndHour(data, day, hour)))
                                .collect(Collectors.toList())
                        ))
                        .collect(Collectors.toList())
        );
    }

    private int countByDayAndHour(Collection<Instant> data, DayOfWeek day, int hour) {
        return data.stream()
                .filter(instant -> instant.atOffset(ZoneOffset.UTC).getDayOfWeek() == day)
                .filter(instant -> instant.atOffset(ZoneOffset.UTC).getHour() == hour)
                .collect(Collectors.toSet())
                .size();
    }

}
