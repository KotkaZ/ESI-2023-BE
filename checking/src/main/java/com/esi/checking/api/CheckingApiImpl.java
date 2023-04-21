package com.esi.checking.api;


import com.esi.checking.CheckingApi;
import com.esi.checking.mapper.CheckingMapper;
import com.esi.checking.models.CheckingDto;
import com.esi.checking.repository.CheckingRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CheckingApiImpl implements CheckingApi {

    private final CheckingMapper checkingMapper;
    private final CheckingRepository checkingRepository;

    @Override
    public ResponseEntity<Void> checkinToRooms(Integer id, CheckingDto bookingCreateDto) {
        //TODO
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> checkoutFromRooms(Integer id, CheckingDto bookingCreateDto) {
        //TODO
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> generateCode(Integer id, CheckingDto bookingCreateDto) {
        //TODO
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
