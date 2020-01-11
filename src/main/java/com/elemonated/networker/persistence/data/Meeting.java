package com.elemonated.networker.persistence.data;

import com.elemonated.networker.model.MeetingDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Meeting {


    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Room room;


    @ManyToOne
    private Employee employeeMeetingLeader;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<Employee> employeesParticipants;

    private String subject;

    //https://www.baeldung.com/hibernate-date-time
    private java.sql.Timestamp sqlTimestampStart;

    private java.sql.Timestamp sqlTimestampEnd;








}
