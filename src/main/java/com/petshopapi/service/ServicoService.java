package com.petshopapi.service;

import com.petshopapi.dto.ServicoRequestDTO;
import com.petshopapi.dto.ServicoResponseDTO;
import com.petshopapi.exception.ResourceNotFoundException;
import com.petshopapi.mapper.ServicoMapper;
import com.petshopapi.model.Servico;
import com.petshopapi.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServicoService {

    @Autowired
    private ServicoRepository repository;

    @Autowired
    private ServicoMapper mapper;

    @Transactional(readOnly = true)
    public List<ServicoResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ServicoResponseDTO buscar(Long id) {
        Servico entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Servico não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public ServicoResponseDTO criar(ServicoRequestDTO dto) {
        Servico entity = mapper.toEntity(dto);
        Servico salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public ServicoResponseDTO atualizar(Long id, ServicoRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Servico não encontrado com id: " + id);
        }
        Servico entity = mapper.toEntity(dto);
        entity.setId(id);
        Servico salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Servico não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
