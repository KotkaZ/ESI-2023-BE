package com.esi.checking.mapper;


import com.esi.checking.dto.CheckingEvent;
import com.esi.checking.model.Checking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CheckingMapper {
    CheckingEvent mapToEvent(Checking checking);

}
