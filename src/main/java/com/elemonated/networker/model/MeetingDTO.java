package com.elemonated.networker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeetingDTO {

    private Long id;

    private Long employeeMeetingLeaderID;

    Set<Long> employeesParticipantsID;

    private Long roomID;

    private String subject;

    private Long startTimestamp;

    private Long endTimestamp;

}
