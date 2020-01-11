package com.elemonated.networker.model;

import com.elemonated.networker.persistence.data.Employee;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Set;

import com.elemonated.networker.persistence.data.Room;
import lombok.Data;

@Data
public class MeetingDTO {
    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd HH:mm");


    private long employeeMeetingLeaderID;

    Set<Long> employeesParticipantsID;

    private long roomID;

    private String subject;

    //https://www.baeldung.com/hibernate-date-time
    private java.util.Date utilTimestamp;


}
