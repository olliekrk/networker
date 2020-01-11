package com.elemonated.networker.rest;


import com.elemonated.networker.persistence.data.Meeting;
import com.elemonated.networker.service.MeetingService;
import com.google.common.primitives.Longs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meeting")
public class MeetingRest {

    private final MeetingService meetingService;

    @Autowired
    private MeetingRest(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    /*
    @GetMapping("/mock")
    public Meeting mockEmployee() {
        Meeting mock = new Meeting();
        mock.se("example@dot.com");
        mock.setPhone("555666777");
        mock.setFirstName("John");
        mock.setLastName("Doe");
        return employeeService.addEmployee(mock);
    }

     */

    @PostMapping
    public Meeting updateMeeting(@RequestBody Meeting newMeeting){
        return meetingService.addMeeting(newMeeting);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable String id){
        Long idLong = Longs.tryParse(id);
        if(idLong == null){

        }else{
            meetingService.deleteMeeting(idLong);
        }
    }


}
