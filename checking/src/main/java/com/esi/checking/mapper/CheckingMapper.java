package com.esi.checking.mapper;


import com.esi.checking.model.Checking;
import com.esi.checking.models.CheckingDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CheckingMapper {

    CheckingDto entityToDto(Checking checking);

    Checking dtoToEntity(CheckingDto checkingDto);

    List<CheckingDto> entitiesToDtoList(List<Checking> checkings);

    List<Checking> dtoListToEntities(List<CheckingDto> checkingDtoList);

}
