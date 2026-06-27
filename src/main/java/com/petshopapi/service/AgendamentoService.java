package com.petshopapi.service;

import com.petshopapi.dto.AgendamentoRequestDTO;
import com.petshopapi.dto.AgendamentoResponseDTO;
import com.petshopapi.exception.ResourceNotFoundException;
import com.petshopapi.mapper.AgendamentoMapper;
import com.petshopapi.model.Agendamento;
import com.petshopapi.repository.AgendamentoRepository;
import com.petshopapi.repository.AnimalRepository;
import com.petshopapi.model.Animal;
import com.petshopapi.repository.ServicoRepository;
import com.petshopapi.model.Servico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;

    @Autowired
    private AgendamentoMapper mapper;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Transactional(readOnly = true)
    public List<AgendamentoResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AgendamentoResponseDTO buscar(Long id) {
        Agendamento entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public AgendamentoResponseDTO criar(AgendamentoRequestDTO dto) {
        Agendamento entity = mapper.toEntity(dto);
        Animal animal = animalRepository.findById(dto.getAnimalId())
            .orElseThrow(() -> new ResourceNotFoundException("Animal não encontrado com id: " + dto.getAnimalId()));
        entity.setAnimal(animal);
        Servico servico = servicoRepository.findById(dto.getServicoId())
            .orElseThrow(() -> new ResourceNotFoundException("Servico não encontrado com id: " + dto.getServicoId()));
        entity.setServico(servico);
        Agendamento salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public AgendamentoResponseDTO atualizar(Long id, AgendamentoRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Agendamento não encontrado com id: " + id);
        }
        Agendamento entity = mapper.toEntity(dto);
        entity.setId(id);
        Animal animal = animalRepository.findById(dto.getAnimalId())
            .orElseThrow(() -> new ResourceNotFoundException("Animal não encontrado com id: " + dto.getAnimalId()));
        entity.setAnimal(animal);
        Servico servico = servicoRepository.findById(dto.getServicoId())
            .orElseThrow(() -> new ResourceNotFoundException("Servico não encontrado com id: " + dto.getServicoId()));
        entity.setServico(servico);
        Agendamento salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Agendamento não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
