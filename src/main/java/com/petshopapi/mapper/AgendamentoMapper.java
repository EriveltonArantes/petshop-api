package com.petshopapi.mapper;

import com.petshopapi.dto.AgendamentoRequestDTO;
import com.petshopapi.dto.AgendamentoResponseDTO;
import com.petshopapi.model.Agendamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AgendamentoMapper {

    @Mapping(target = "animal", ignore = true)
    @Mapping(target = "servico", ignore = true)
    Agendamento toEntity(AgendamentoRequestDTO dto);

    @Mapping(target = "animalId", source = "animal.id")
    @Mapping(target = "servicoId", source = "servico.id")
    AgendamentoResponseDTO toResponseDTO(Agendamento entity);
}
