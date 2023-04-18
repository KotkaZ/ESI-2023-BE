package com.esi.rooms.api;

import static com.esi.rooms.models.RoomDto.StateEnum.BOOKED;
import static com.esi.rooms.models.RoomDto.StateEnum.CLEAN;
import static com.esi.rooms.models.RoomDto.StateEnum.READY;

import com.esi.rooms.RoomsApi;
import com.esi.rooms.models.RoomDto;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomsApiImpl implements RoomsApi {

    @Override
    public ResponseEntity<List<RoomDto>> getRooms() {
        return ResponseEntity.ok(
            List.of(
                RoomDto.builder().id(0).number(100).state(BOOKED).build(),
                RoomDto.builder().id(1).number(101).state(CLEAN).build(),
                RoomDto.builder().id(2).number(102).state(READY).build()
            )
        );
    }
}
