package com.petshopapi.mapper;

import com.petshopapi.dto.AnimalRequestDTO;
import com.petshopapi.dto.AnimalResponseDTO;
import com.petshopapi.model.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnimalMapper {

    Animal toEntity(AnimalRequestDTO dto);

    AnimalResponseDTO toResponseDTO(Animal entity);
}
