package com.elemonated.networker.service;

import com.elemonated.networker.model.TaskAssignmentInfo;
import com.elemonated.networker.model.TaskDTO;
import com.elemonated.networker.persistence.data.Employee;
import com.elemonated.networker.persistence.data.Task;
import com.elemonated.networker.persistence.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final EmployeeService employeeService;

    @Autowired
    private TaskService(TaskRepository taskRepository, EmployeeService employeeService) {
        this.taskRepository = taskRepository;
        this.employeeService = employeeService;
    }

    public Task addTask(TaskDTO taskDto) {
        Optional<Employee> employee = employeeService.getEmployeeById(taskDto.getEmployeeId());
        Task task = new Task(taskDto, employee.orElse(null));
        return taskRepository.save(task);
    }

    public Task assignTask(TaskAssignmentInfo taskAssignmentInfo) throws Exception {
        Optional<Task> task = taskRepository.findById(taskAssignmentInfo.getTaskId());
        Optional<Employee> employee = employeeService.getEmployeeById(taskAssignmentInfo.getEmployeeId());

        if (employee.isEmpty()) {
            throw new Exception("Employee not found.");
        }

        if (task.isEmpty()) {
            throw new Exception("Task not found.");
        }

        task.get().assignToEmployee(employee.get(), taskAssignmentInfo.getStartTimestamp());
        return taskRepository.save(task.get());
    }

    public Task updateCompletionDate(Long taskId) throws Exception {
        Optional<Task> task = taskRepository.findById(taskId);
        if(task.isEmpty()){
            throw new Exception("Task is not found!");
        }
        if(task.get().getEmployee() == null){
            throw new Exception("Task is not started!");
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        task.get().setCompletionTimestamp(timestamp);

        return task.get();
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }


}
