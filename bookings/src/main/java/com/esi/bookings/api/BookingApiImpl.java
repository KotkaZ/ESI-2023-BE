package com.esi.bookings.api;

import com.esi.bookings.BookingsApi;
import com.esi.bookings.models.BookingDto;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingApiImpl implements BookingsApi {

    @Override
    public ResponseEntity<List<BookingDto>> getBookings() {
        return BookingsApi.super.getBookings();
    }
}
