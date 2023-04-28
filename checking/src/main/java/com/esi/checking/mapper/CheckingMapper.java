package com.esi.checking.mapper;


import com.esi.checking.dto.CheckingEvent;
import com.esi.checking.model.Checking;
import com.esi.checking.models.CheckingDto;
import com.esi.checking.models.CodeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CheckingMapper {
    CheckingEvent mapToEvent(Checking checking);

    Checking checkingDtoToEntity(CheckingDto checkingDto);

    CheckingDto entityToDto(Checking checking);

    CodeDto entityToCodeDto(Checking checking);

}
