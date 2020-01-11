package com.elemonated.networker.rest;

import com.elemonated.networker.persistence.data.Employee;
import com.elemonated.networker.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/employee")
public class EmployeeRest {

    private final EmployeeService employeeService;

    @Autowired
    private EmployeeRest(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/mock")
    public Employee mockEmployee() {
        Employee mock = new Employee();
        mock.setEmail("example@dot.com");
        mock.setPhone("555666777");
        mock.setFirstName("John");
        mock.setLastName("Doe");
        return employeeService.addEmployee(mock);
    }

    @PostMapping
    public Employee updateEmployee(@Valid @RequestBody Employee newEmployee){
        return employeeService.addEmployee(newEmployee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@Valid  @PathVariable Long id){
        employeeService.deleteEmployee(id);
    }
}
