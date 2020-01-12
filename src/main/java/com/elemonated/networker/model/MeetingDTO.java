package com.elemonated.networker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeetingDTO {

    private long id;

    private long employeeMeetingLeaderID;

    Set<Long> employeesParticipantsID;

    private long roomID;

    private String subject;

    //https://www.baeldung.com/hibernate-date-time
//    private Long utilTimestampStartLong;
//
//    private Long utilTimestampEndLong;

}
