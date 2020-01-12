package com.elemonated.networker.rest;


import com.elemonated.networker.model.MeetingDTO;
import com.elemonated.networker.persistence.data.Meeting;
import com.elemonated.networker.service.MeetingService;
import com.google.common.primitives.Longs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/meeting")
public class MeetingRest {

    private final MeetingService meetingService;

    @Autowired
    private MeetingRest(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @PostMapping
    public Meeting saveMeeting(@RequestBody MeetingDTO meetingDTO) {
        return meetingService.addMeeting(meetingDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable String id) {
        Long idLong = Longs.tryParse(id);
        if (idLong != null) {
            meetingService.deleteMeeting(idLong);
        }
    }

    @GetMapping("/all")
    public List<MeetingDTO> getAllMeetings() {
        List<MeetingDTO> meetingDTOList = new ArrayList<>();
        for (Meeting m : meetingService.getAllMeetings()) meetingDTOList.add(m.toDTO());
        return meetingDTOList;
    }

}
