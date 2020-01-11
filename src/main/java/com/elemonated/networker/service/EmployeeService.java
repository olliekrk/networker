package com.elemonated.networker.service;

import com.elemonated.networker.persistence.data.Employee;
import com.elemonated.networker.persistence.repository.EmployeeRepository;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        logger.info("Employee saved: " + savedEmployee);
        return savedEmployee;
    }

    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

    public Optional<Employee> getEmployeeById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return employeeRepository.findById(id);
    }

    public List<Employee> getAllEmployees() {
        logger.info("Getting all employees");
        return Lists.newArrayList(employeeRepository.findAll());
    }
}
