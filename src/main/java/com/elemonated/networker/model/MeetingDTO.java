package com.elemonated.networker.model;

import lombok.Data;

import java.util.Set;

@Data
public class MeetingDTO {

    private long employeeMeetingLeaderID;

    Set<Long> employeesParticipantsID;

    private long roomID;

    private String subject;

    //https://www.baeldung.com/hibernate-date-time
    private java.sql.Timestamp utilTimestamp;


}
