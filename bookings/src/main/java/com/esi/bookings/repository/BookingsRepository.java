package com.esi.bookings.repository;

import com.esi.bookings.model.Booking;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookingsRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findAllByUserId(Integer userId);

    // A.end >= B.start AND A.start <= B.end
    @Query("""
        SELECT
            CASE WHEN
              (b.startDate BETWEEN :startDate AND :endDate) OR
              (b.endDate BETWEEN :startDate AND :endDate) OR
              (b.startDate <= :startDate AND b.endDate >= :endDate) OR
              (b.startDate >= :startDate AND b.endDate <= :endDate)
            THEN
                TRUE
            ELSE
                FALSE
            END
        FROM Booking b
        WHERE b.roomId = :roomId
        """)
    boolean existsBookingInSpecificTimeRange(
            @Param("roomId") Integer roomId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
