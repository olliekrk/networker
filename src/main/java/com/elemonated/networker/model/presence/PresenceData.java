package com.elemonated.networker.model.presence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PresenceData {
    private List<PresenceDailyInfo> dailyData;
}
