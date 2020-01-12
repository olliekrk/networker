package com.elemonated.networker.persistence.repository;

import com.elemonated.networker.persistence.data.GoogleCalendar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoogleCalendarRepository extends CrudRepository<GoogleCalendar, String> {
}
