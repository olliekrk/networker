package com.elemonated.networker.service;

import com.elemonated.networker.model.MeetingDTO;
import com.elemonated.networker.persistence.data.Meeting;
import com.elemonated.networker.persistence.repository.MeetingRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class MeetingService {
    private final MeetingRepository meetingRepository;
    private final EmployeeService employeeService;
    private final RoomService roomService;


    @Autowired
    private MeetingService(MeetingRepository meetingRepository, EmployeeService employeeService, RoomService roomService) {
        this.meetingRepository = meetingRepository;
        this.employeeService = employeeService;
        this.roomService = roomService;
    }

    public Meeting addMeeting(MeetingDTO meetingDTO) {
        Meeting meeting = new Meeting();
        meeting.setSubject(meetingDTO.getSubject());

        Optional.ofNullable(meetingDTO.getId())
                .ifPresent(meeting::setId);

        employeeService.getEmployeeById(meetingDTO.getEmployeeMeetingLeaderID()).ifPresent(meeting::setEmployeeMeetingLeader);

        Optional.ofNullable(meetingDTO.getRoomID())
                .flatMap(roomService::getRoomById)
                .ifPresent(meeting::setRoom);

        Optional.ofNullable(meetingDTO.getStartTimestamp())
                .map(Instant::ofEpochMilli)
                .map(Timestamp::from)
                .ifPresent(meeting::setStartTimestamp);

        Optional.ofNullable(meetingDTO.getEndTimestamp())
                .map(Instant::ofEpochMilli)
                .map(Timestamp::from)
                .ifPresent(meeting::setEndTimestamp);

        return meetingRepository.save(meeting);
    }

    public void deleteMeeting(long id) {
        meetingRepository.deleteById(id);
    }

    public List<Meeting> getAllMeetings() {
        return Lists.newArrayList(meetingRepository.findAll());
    }
}
