package com.elemonated.networker.rest;

import com.elemonated.networker.persistence.data.Room;
import com.elemonated.networker.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/room")
public class RoomRest {
    private final RoomService roomService;

    @Autowired
    private RoomRest(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public Room addRoom(@Valid @RequestBody Room room){
        return roomService.addRoom(room);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@Valid @PathVariable Long id){
        roomService.deleteRoomById(id);
    }

}
