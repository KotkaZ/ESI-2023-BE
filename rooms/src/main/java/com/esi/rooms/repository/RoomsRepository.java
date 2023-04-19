package com.esi.rooms.repository;

import com.esi.rooms.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomsRepository extends JpaRepository<Room, Integer> {
}
