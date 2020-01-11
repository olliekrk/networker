package com.elemonated.networker.persistence.repository;

import com.elemonated.networker.persistence.data.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
}
