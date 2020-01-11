package com.elemonated.networker.persistence.repository;

import com.elemonated.networker.persistence.data.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
}
