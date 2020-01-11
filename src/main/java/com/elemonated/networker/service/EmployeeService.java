package com.elemonated.networker.service;

import com.elemonated.networker.persistence.data.Employee;
import com.elemonated.networker.persistence.repository.EmployeeRepository;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

    public Optional<Employee> getEmployeeById (Long id) {
        return employeeRepository.findById(id);
    }

}
