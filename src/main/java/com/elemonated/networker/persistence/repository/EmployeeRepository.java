package com.elemonated.networker.persistence.repository;

import com.elemonated.networker.persistence.data.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
