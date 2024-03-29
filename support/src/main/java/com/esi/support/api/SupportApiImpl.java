package com.esi.support.api;


import com.esi.support.SupportApi;
import com.esi.support.mapper.SupportMapper;
import com.esi.support.models.DirtyEntityDto;
import com.esi.support.models.SupportDto;
import com.esi.support.repository.SupportRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
public class SupportApiImpl implements SupportApi {

    private final SupportMapper supportMapper;
    private final SupportRepository supportRepository;

    @Override
    public ResponseEntity<SupportDto> getRequestById(Integer id) {
        val checking = supportRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Checkin not found"));
        return ResponseEntity.ok(supportMapper.entityToDto(checking));
    }

    @Override
    public ResponseEntity<List<DirtyEntityDto>> getCleaningRequests() {
        val requests = supportRepository.findAll();

        return ResponseEntity.ok(
            supportMapper.entitiesToDtoList(requests)
        );
    }

    @Override
    public ResponseEntity<Void> startCleaningRequest(Integer id, Integer userId) {


        val cleaningRequest = supportRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Cleaning request not found"));

        cleaningRequest.setAssignedTo(userId);
        cleaningRequest.setCleaningStartedAt(OffsetDateTime.now());

        supportRepository.save(cleaningRequest);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> endCleaningRequest(Integer id) {


        val cleaningRequest = supportRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Cleaning request not found"));

        cleaningRequest.setCleanedAt(OffsetDateTime.now());

        supportRepository.save(cleaningRequest);

        return ResponseEntity.ok().build();
    }

}
