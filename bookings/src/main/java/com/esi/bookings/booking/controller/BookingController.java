package com.esi.bookings.booking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BookingController {

    @GetMapping("/bookings/{code}")
    public Integer testEndpoint(@PathVariable String code) {
        return 200;
    }   
}