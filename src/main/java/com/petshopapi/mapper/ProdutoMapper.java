package com.petshopapi.mapper;

import com.petshopapi.dto.ProdutoRequestDTO;
import com.petshopapi.dto.ProdutoResponseDTO;
import com.petshopapi.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    Produto toEntity(ProdutoRequestDTO dto);

    ProdutoResponseDTO toResponseDTO(Produto entity);
}
