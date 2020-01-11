package com.elemonated.networker.model.presence;

import lombok.Data;

@Data
public class PresenceHourlyInfo {
    private Integer hour; // range from 0 to 23
    private Double percentage;
}
