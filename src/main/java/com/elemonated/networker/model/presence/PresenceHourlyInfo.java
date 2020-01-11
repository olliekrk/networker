package com.elemonated.networker.model.presence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PresenceHourlyInfo {
    private Integer hour; // range from 0 to 23
    private Double percentage;
}
