package com.esi.support.mapper;

import com.esi.support.models.DirtyEntityDto;
import com.esi.support.model.Support;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupportMapper {
    DirtyEntityDto entityToDto(Support support);

    Support dtoToEntity(DirtyEntityDto checkingDto);
    List<DirtyEntityDto> entitiesToDtoList(List<Support> requests);

    List<Support> dtoListToEntities(List<DirtyEntityDto> checkingDtoList);
}
