package com.elemonated.networker.model.presence;

import lombok.Data;

import java.util.List;

@Data
public class PresenceData {
    private List<PresenceDailyInfo> dailyData;
}
