package com.esi.rooms.api;


import com.esi.rooms.RoomsApi;
import com.esi.rooms.mapper.RoomsMapper;
import com.esi.rooms.models.RoomDto;
import com.esi.rooms.repository.RoomsRepository;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class RoomsApiImpl implements RoomsApi {

    private final RoomsMapper roomsMapper;
    private final RoomsRepository roomsRepository;

    @Override
    public ResponseEntity<List<RoomDto>> getRooms() {
        val rooms = roomsRepository.findAll();

        return ResponseEntity.ok(
            roomsMapper.entitiesToDtoList(rooms)
        );
    }

    @Override
    public ResponseEntity<RoomDto> getRoomById(Integer id) {
        val room = roomsRepository.findById(id);
        return room
        .map( r -> ResponseEntity.ok(
            roomsMapper.entityToDto(room.get())
        ))
        .orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found")
        );
    }

    @Override
    public ResponseEntity<Void> createRoom(RoomDto roomDto) {
        val roomS = roomsRepository.findById(roomDto.getRoomNumber());
        if (roomS.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Room already exists");
        }
        val room = roomsMapper.dtoToEntity(roomDto);
        roomsRepository.save(room);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> editRoom(Integer id, RoomDto roomDto) {
        val priceIsSubZero = roomDto.getPrice().compareTo(BigDecimal.valueOf(0.0)) < 0;
        if (roomDto.getGuestsMaxNumber() < 1 || priceIsSubZero) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid input");
        }
        val room = roomsRepository.findById(roomDto.getRoomNumber())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found"));

        room.setDescription(roomDto.getDescription());
        room.setGuestsMaxNumber(roomDto.getGuestsMaxNumber());
        room.setPrice(roomDto.getPrice());

        roomsRepository.save(room);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<RoomDto> removeRoom(Integer id) {
        roomsRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
