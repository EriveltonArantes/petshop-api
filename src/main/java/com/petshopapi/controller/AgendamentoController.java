package com.petshopapi.controller;

import com.petshopapi.dto.AgendamentoRequestDTO;
import com.petshopapi.dto.AgendamentoResponseDTO;
import com.petshopapi.service.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Agendamento", description = "Gerenciamento de agendamentos")
@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

    @Operation(summary = "Listar todos os Agendamento")
    @GetMapping
    public List<AgendamentoResponseDTO> listar(@RequestParam(required = false) String observacoes, @RequestParam(required = false) Long animalId, @RequestParam(required = false) Long servicoId) {
        List<AgendamentoResponseDTO> resultado = service.listar();
        if (observacoes != null && !observacoes.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getObservacoes() != null &&
                item.getObservacoes().toLowerCase().contains(observacoes.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        if (animalId != null) {
            resultado = resultado.stream().filter(item -> animalId.equals(item.getAnimalId())).collect(java.util.stream.Collectors.toList());
        }
        if (servicoId != null) {
            resultado = resultado.stream().filter(item -> servicoId.equals(item.getServicoId())).collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @Operation(summary = "Buscar Agendamento por ID")
    @GetMapping("/{id}")
    public AgendamentoResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar Agendamento")
    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> criar(@Valid @RequestBody AgendamentoRequestDTO agendamento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(agendamento));
    }

    @Operation(summary = "Atualizar Agendamento")
    @PutMapping("/{id}")
    public AgendamentoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody AgendamentoRequestDTO agendamento) {
        return service.atualizar(id, agendamento);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir Agendamento")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
