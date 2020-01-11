package com.elemonated.networker.service;

import com.elemonated.networker.persistence.data.Employee;
import com.elemonated.networker.persistence.data.Room;
import com.elemonated.networker.persistence.repository.RoomRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    private RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room addRoom(Room room){
        return roomRepository.save(room);
    }

    public void deleteRoomById(Long id){
        roomRepository.deleteById(id);
    }

    public Optional<Room> getRoomById (Long id) {
        return roomRepository.findById(id);
    }


    public List<Room> getAllRooms() {
        return Lists.newArrayList(roomRepository.findAll());
    }

}
