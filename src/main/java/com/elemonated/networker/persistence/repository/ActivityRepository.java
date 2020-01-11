package com.elemonated.networker.persistence.repository;

import com.elemonated.networker.persistence.data.Activity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActivityRepository extends CrudRepository<Activity, Long> {
    List<Activity> findByEmployee_id(Long employeeId);
}
