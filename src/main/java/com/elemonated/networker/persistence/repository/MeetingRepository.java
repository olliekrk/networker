package com.elemonated.networker.persistence.repository;

import com.elemonated.networker.persistence.data.Employee;
import com.elemonated.networker.persistence.data.Meeting;
import org.springframework.data.repository.CrudRepository;

public interface MeetingRepository extends CrudRepository<Meeting, Long> {
}
