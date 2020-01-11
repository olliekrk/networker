package com.elemonated.networker.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TaskDTO {
    private Long employeeId;

    private String description;

    private Timestamp startTimestamp;

    private Timestamp completionTimestamp;

    private Long prize;
}
