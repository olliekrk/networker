package com.elemonated.networker.service;

import com.elemonated.networker.model.MeetingDTO;
import com.elemonated.networker.persistence.data.Employee;
import com.elemonated.networker.persistence.data.Meeting;
import com.elemonated.networker.persistence.data.Room;
import com.elemonated.networker.persistence.repository.MeetingRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        Optional<Employee> employeeLeader = employeeService.getEmployeeById(meetingDTO.getEmployeeMeetingLeaderID());
        if (employeeLeader.isPresent()) {
            meeting.setEmployeeMeetingLeader(employeeLeader.get());
        }

        Optional<Room> room = roomService.getRoomById(meetingDTO.getRoomID());
        if (room.isPresent()) {
            meeting.setRoom(room.get());
        }

//            java.sql.Timestamp sqlTimestampNew = new Timestamp(meetingDTO.getUtilTimestampStartLong());
//            meeting.setSqlTimestampStart(sqlTimestampNew);
//            sqlTimestampNew = new Timestamp(meetingDTO.getUtilTimestampEndLong());
//            meeting.setSqlTimestampEnd(sqlTimestampNew);


        return meetingRepository.save(meeting);

    }

    public void deleteMeeting(long id) {
        meetingRepository.deleteById(id);
    }

    public List<Meeting> getAllMeetings() {
        return Lists.newArrayList(meetingRepository.findAll());
    }
}
