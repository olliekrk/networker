package com.elemonated.networker.persistence.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Room {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private Integer capacity;
}
