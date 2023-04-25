package com.esi.checking.api;


import com.esi.checking.CheckingApi;
import com.esi.checking.mapper.CheckingMapper;
import com.esi.checking.models.CheckingDto;
import com.esi.checking.models.CodeDto;
import com.esi.checking.models.GetCode200ResponseDto;
import com.esi.checking.repository.CheckingRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
public class CheckingApiImpl implements CheckingApi {

    private final CheckingMapper checkingMapper;
    private final CheckingRepository checkingRepository;

    @Override
    public ResponseEntity<CheckingDto> getCheckingById(Integer bookingId) {
        val checking = checkingRepository.findById(bookingId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Checkin not found"));
        return ResponseEntity.ok(checkingMapper.entityToDto(checking));
    }

    @Override
    public ResponseEntity<Void> checkinToRooms(Integer bookingId) {
        val checkingIn = checkingRepository.findById(bookingId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "You have already checked in"));

        checkingIn.setCheckInAt(OffsetDateTime.now());

        checkingRepository.save(checkingIn);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> checkoutFromRooms(Integer bookingId) {
        val checkingIn = checkingRepository.findById(bookingId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "You have already checked out"));

        checkingIn.setCheckOutAt(OffsetDateTime.now());

        checkingRepository.save(checkingIn);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<CodeDto> generateCode(Integer bookingId) {
        val checkingIn = checkingRepository.findById(bookingId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "You have already checked out"));

        checkingIn.setCode(generateCodeString());

        checkingRepository.save(checkingIn);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private String generateCodeString(){
        int min = 0;
        int max = 99999;
        int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);

        String codeString = String.valueOf(random_int);
        int codeStringLen = codeString.length();
        return "0".repeat(5-codeStringLen) + codeString;
    }

    @Override
    public ResponseEntity<CodeDto> getCode(Integer bookingId) {
        val code = checkingRepository.findById(bookingId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Checkin not found"));
        return ResponseEntity.ok(checkingMapper.entityToCodeDto(code));
    }
}
