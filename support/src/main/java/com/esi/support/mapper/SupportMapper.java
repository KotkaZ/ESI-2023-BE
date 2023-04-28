package com.esi.support.mapper;

import com.esi.support.models.DirtyEntityDto;
import com.esi.support.model.Support;
import com.esi.support.models.SupportDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupportMapper {
    SupportDto entityToDto(Support support);

    List<DirtyEntityDto> entitiesToDtoList(List<Support> requests);

}
