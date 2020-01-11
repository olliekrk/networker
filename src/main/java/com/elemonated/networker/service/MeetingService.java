package com.elemonated.networker.service;

import com.elemonated.networker.persistence.data.Employee;
import com.elemonated.networker.persistence.data.Meeting;
import com.elemonated.networker.persistence.repository.EmployeeRepository;
import com.elemonated.networker.persistence.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeetingService {
    private final MeetingRepository meetingRepository;

    @Autowired
    private MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public Meeting addMeeting(Meeting meeting) {
        return meetingRepository.save(meeting);
    }

    public void deleteMeeting(long id) {
        meetingRepository.deleteById(id);
    }
}
