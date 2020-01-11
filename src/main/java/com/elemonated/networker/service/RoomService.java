package com.elemonated.networker.service;

import com.elemonated.networker.persistence.data.Room;
import com.elemonated.networker.persistence.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
