package com.elemonated.networker.persistence.data;

import com.elemonated.networker.model.TaskDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
public class Task {
    public Task(TaskDTO taskDTO, Employee employee) {
        this.description = taskDTO.getDescription();
        this.startTimestamp = taskDTO.getStartTimestamp();
        this.completionTimestamp = taskDTO.getCompletionTimestamp();
        this.prize = taskDTO.getPrize();
        this.employee = employee;
    }

    public void assignToEmployee(Employee employee, Timestamp startTimestamp) {
        this.employee = employee;
        this.startTimestamp = startTimestamp;
    }

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Employee employee;

    private String description;

    private Timestamp startTimestamp;

    private Timestamp completionTimestamp;

    private Long prize;
}
