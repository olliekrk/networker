package com.elemonated.networker.persistence.data;

import com.elemonated.networker.model.MeetingDTO;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
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

    private Timestamp startTimestamp;

    private Timestamp endTimestamp;

    public MeetingDTO toDTO() {
        return new MeetingDTO(
                id,
                Optional.ofNullable(employeeMeetingLeader).map(Employee::getId).orElse(null),
                employeesParticipants.stream().map(Employee::getId).collect(Collectors.toSet()),
                Optional.ofNullable(room).map(Room::getId).orElse(null),
                subject,
                Optional.ofNullable(startTimestamp).map(Timestamp::getTime).orElse(null),
                Optional.ofNullable(endTimestamp).map(Timestamp::getTime).orElse(null)
        );
    }

}
