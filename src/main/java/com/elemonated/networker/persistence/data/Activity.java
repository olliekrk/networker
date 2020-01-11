package com.elemonated.networker.persistence.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
public class Activity {

    @Id
    @GeneratedValue
    private Long id;

    private Timestamp startTimestamp;

    private Timestamp endTimestamp;

    @ManyToOne
    private Employee employee;

}
