package com.elemonated.networker.persistence.repository;

import com.elemonated.networker.persistence.data.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Employee findByEmail(String email);
}
