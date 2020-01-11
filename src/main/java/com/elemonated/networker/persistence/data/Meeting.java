package com.elemonated.networker.persistence.data;

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
    private Employee employeeMeetingLeader;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<Employee> employeesParticipants;

    String subject;

    //https://www.baeldung.com/hibernate-date-time
    @Basic
    @Temporal(TemporalType.DATE)
    private java.util.Date utilDate;

    @Basic
    @Temporal(TemporalType.TIME)
    private java.util.Date utilTime;

    /*
    @ManyToOne
    private Room room;
     */


}
