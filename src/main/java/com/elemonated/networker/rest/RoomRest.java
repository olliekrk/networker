package com.elemonated.networker.rest;

import com.elemonated.networker.persistence.data.Employee;
import com.elemonated.networker.persistence.data.Room;
import com.elemonated.networker.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    public void deleteRoom(@PathVariable Long id){
        roomService.deleteRoomById(id);
    }

    /*
    @GetMapping("/all")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }
    */
    @GetMapping("/{id}")
    public Optional<Room> getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }


}
