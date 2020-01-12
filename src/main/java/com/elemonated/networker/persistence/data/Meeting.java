package com.elemonated.networker.persistence.data;

import com.elemonated.networker.model.MeetingDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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


    public MeetingDTO toDTO()
    {

        long leaderID = 0;
        if(employeeMeetingLeader != null)
        {
            leaderID = employeeMeetingLeader.getId();
        }
        long roomID = 0;
        if(room != null)
        {
            roomID = room.getId();
        }

        return new MeetingDTO(
                id,
                leaderID,
                employeesParticipants.stream().map(Employee::getId).collect(Collectors.toSet()),
                roomID,
                subject);
    }





}
