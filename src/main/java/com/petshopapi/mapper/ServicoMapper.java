package com.petshopapi.mapper;

import com.petshopapi.dto.ServicoRequestDTO;
import com.petshopapi.dto.ServicoResponseDTO;
import com.petshopapi.model.Servico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServicoMapper {

    Servico toEntity(ServicoRequestDTO dto);

    ServicoResponseDTO toResponseDTO(Servico entity);
}
