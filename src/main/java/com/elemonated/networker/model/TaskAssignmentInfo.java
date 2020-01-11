package com.elemonated.networker.model;

import lombok.Data;

import java.sql.Timestamp;

import static java.lang.System.currentTimeMillis;

@Data
public class TaskAssignmentInfo {
    private Long taskId;
    private Long employeeId;
    private Timestamp startTimestamp;

    public TaskAssignmentInfo(Long taskId, Long employeeId) {
        this.taskId = taskId;
        this.employeeId = employeeId;
        setCurrentTimestamp();
    }

    public void setCurrentTimestamp() {
        this.startTimestamp = new Timestamp(currentTimeMillis());
    }
}
