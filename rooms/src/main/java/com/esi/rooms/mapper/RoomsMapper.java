package com.esi.rooms.mapper;


import com.esi.rooms.model.Room;
import com.esi.rooms.models.RoomDto;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomsMapper {

    RoomDto entityToDto(Room room);

    Room dtoToEntity(RoomDto roomDto);

    List<RoomDto> entitiesToDtoList(List<Room> rooms);

    List<Room> dtoListToEntities(List<RoomDto> roomDtoList);

}
