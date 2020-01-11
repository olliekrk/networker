package com.elemonated.networker.service;

import com.elemonated.networker.model.MeetingDTO;
import com.elemonated.networker.persistence.data.Employee;
import com.elemonated.networker.persistence.data.Meeting;
import com.elemonated.networker.persistence.data.Room;
import com.elemonated.networker.persistence.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
        try {
            Optional<Employee> employeeLeader = employeeService.getEmployeeById(meetingDTO.getEmployeeMeetingLeaderID());
            if (employeeLeader.isEmpty()) {
                throw new Exception("brak lidera");
            }
            meeting.setEmployeeMeetingLeader(employeeLeader.get());

            for (Long id : meetingDTO.getEmployeesParticipantsID()) {
                Optional<Employee> employee = employeeService.getEmployeeById(id);
                if (employee.isEmpty()) {
                    throw new Exception("brak zbioru uczesnikow");
                }
                meeting.getEmployeesParticipants().add(employee.get());
            }
            Optional<Room> room = roomService.getRoomById(meetingDTO.getRoomID());
            if (room.isEmpty()) {
                throw new Exception("brak pokoju");
            }
            meeting.setRoom(room.get());
            meeting.setSubject(meetingDTO.getSubject());
            java.sql.Timestamp sqlTimestampNew = new Timestamp(meetingDTO.getUtilTimestampStartLong());
            meeting.setSqlTimestampStart(sqlTimestampNew);
            sqlTimestampNew = new Timestamp(meetingDTO.getUtilTimestampEndLong());
            meeting.setSqlTimestampEnd(sqlTimestampNew);


        }
        catch(Exception e)
        {
            Meeting meetingMock = new Meeting();
            meetingMock.setSubject(e.getMessage());
            return meetingRepository.save(meetingMock);

        }
        return meetingRepository.save(meeting);

    }

    public void deleteMeeting(long id) {
        meetingRepository.deleteById(id);
    }
}
