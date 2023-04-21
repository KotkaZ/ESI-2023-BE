package com.esi.bookings.repository;

import com.esi.bookings.model.Booking;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingsRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findAllByUserId(Integer userId);

    Optional<Booking> findByRoomId(Integer roomId);
}
