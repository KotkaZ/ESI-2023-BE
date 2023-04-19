package com.esi.rooms.repository;

import com.esi.rooms.model.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository  extends CrudRepository<Room, Integer> {
}
