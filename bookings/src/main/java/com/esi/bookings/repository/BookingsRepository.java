package com.esi.bookings.repository;

import com.esi.bookings.model.Booking;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookingsRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findAllByUserId(Integer userId);

    @Query("SELECT case when (count(b) > 0)  then true else false end FROM Booking b "
        + "WHERE b.roomId = :roomId AND b.startDate > :endDate AND b.endDate < :startDate")
    boolean existsBookingInSpecificTimeRange(
            @Param("roomId") Integer roomId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
