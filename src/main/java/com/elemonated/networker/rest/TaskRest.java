package com.elemonated.networker.rest;

import com.elemonated.networker.model.TaskAssignmentInfo;
import com.elemonated.networker.model.TaskDTO;
import com.elemonated.networker.persistence.data.Task;
import com.elemonated.networker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/task")
public class TaskRest {
    private final TaskService taskService;

    @Autowired
    private TaskRest(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task addTask(@RequestBody TaskDTO taskDTO) {
        return taskService.addTask(taskDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTaskById(@PathVariable Long id) {
        taskService.deleteTaskById(id);
    }

    @PostMapping("/start")
    public Task startTask(@RequestBody TaskAssignmentInfo taskAssignmentInfo) {
        taskAssignmentInfo.setCurrentTimestamp();
        try {
            return taskService.assignTask(taskAssignmentInfo);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PatchMapping("/{id}")
    public Task completeTask(@PathVariable Long id) {
        try {
            return taskService.updateCompletionDate(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
