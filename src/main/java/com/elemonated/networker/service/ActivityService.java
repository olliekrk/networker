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
//
//        Map<DayOfWeek, Set<Instant>> aggregatedData = allRecordedActivitiesData
//                .stream()
//                .collect(
//                        HashMap::new,
//                        (acc, element) -> {
//                            DayOfWeek dayOfWeek = element.atOffset(ZoneOffset.UTC).getDayOfWeek();
//                            acc.computeIfAbsent(dayOfWeek, __ -> new HashSet<>());
//                            acc.get(dayOfWeek).add(element);
//                        },
//                        (accA, accB) -> accB.forEach((key, value) -> {
//                            accA.computeIfAbsent(key, __ -> new HashSet<>());
//                            accA.get(key).addAll(value);
//                        })
//                );
//
//        Map<DayOfWeek, Map<Integer, Integer>> countedData = aggregatedData.entrySet()
//                .stream()
//                .map(entry -> Map.entry(
//                        entry.getKey(),
//                        entry.getValue().stream().collect(
//                                (Supplier<HashMap<Integer, Integer>>) HashMap::new,
//                                (acc, element) -> {
//                                    int hour = element.atOffset(ZoneOffset.UTC).getHour();
//                                    acc.put(hour, acc.getOrDefault(hour, 0) + 1);
//                                },
//                                (accA, accB) -> accB.forEach((hour, value) -> accA.put(hour, accA.getOrDefault(hour, 0) + value))
//                        )))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//
//        Integer maxHourlyValue = countedData
//                .values()
//                .stream()
//                .map(acc -> acc.values().stream().max(Integer::compareTo).orElse(0))
//                .max(Integer::compareTo).orElse(1);
//
//        List<PresenceDailyInfo> collectedData = countedData.entrySet().stream().map(entry ->
//                new PresenceDailyInfo(
//                        entry.getKey().name(),
//                        entry.getValue().entrySet().stream()
//                                .map(hourEntry -> new PresenceHourlyInfo(hourEntry.getKey(), hourEntry.getValue().doubleValue() / maxHourlyValue))
//                                .collect(Collectors.toList())
//                )
//        ).collect(Collectors.toList());

        PresenceData computedResult = computePresenceData(allRecordedActivitiesData);
        logger.info("Computed activity data:" + computedResult);
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
