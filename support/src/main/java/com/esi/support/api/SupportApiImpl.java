package com.esi.support.api;


import com.esi.support.SupportApi;
import com.esi.support.mapper.SupportMapper;
import com.esi.support.models.SupportDto;
import com.esi.support.repository.SupportRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SupportApiImpl implements SupportApi {

    private final SupportMapper SupportMapper;
    private final SupportRepository SupportRepository;

    @Override
    public ResponseEntity<List<SupportDto>> getCleaningRequests() {
        val requests = SupportRepository.findAll();

        return ResponseEntity.ok(
                SupportMapper.entitiesToDtoList(requests)
        );
    }

    @Override
    public ResponseEntity<Void> createCleaningRequest(Integer roomId, SupportDto SupportDto) {
        // TODO create cleaning request using room id

        val Support = SupportMapper.dtoToEntity(SupportDto);
        SupportRepository.save(Support);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> endCleaningRequest(Integer roomId, SupportDto SupportDto) {
        // TODO edit cleaning request using room id

        val Support = SupportMapper.dtoToEntity(SupportDto);
        SupportRepository.save(Support);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}
