package com.esi.bookings.booking.controller;

import org.openapitools.api.BusLinesApi;
import org.openapitools.model.BusLine;
import org.springframework.http.ResponseEntity;

public class BusLinesApiImpl implements org.openapitools.api.BusLinesApi {
    @Override
    public ResponseEntity<BusLine> createBusLine(BusLine busLine) {
        return BusLinesApi.super.createBusLine(busLine);
    }
}
