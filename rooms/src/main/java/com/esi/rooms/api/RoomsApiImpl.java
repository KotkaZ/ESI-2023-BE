package com.esi.rooms.api;


import com.esi.rooms.RoomsApi;
import com.esi.rooms.mapper.RoomsMapper;
import com.esi.rooms.models.RoomDto;
import com.esi.rooms.repository.RoomsRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
}
