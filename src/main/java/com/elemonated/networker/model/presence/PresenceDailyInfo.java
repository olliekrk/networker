package com.elemonated.networker.model.presence;

import lombok.Data;

import java.util.List;

@Data
public class PresenceDailyInfo {
    private List<PresenceHourlyInfo> hourlyData;
    private String dayOfWeek;
}
