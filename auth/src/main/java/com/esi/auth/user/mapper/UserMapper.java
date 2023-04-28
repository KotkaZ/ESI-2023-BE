package com.esi.auth.user.mapper;

import com.esi.auth.user.model.User;
import com.esi.bookings.models.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User dtoToEntity(UserDto userDto);
}
