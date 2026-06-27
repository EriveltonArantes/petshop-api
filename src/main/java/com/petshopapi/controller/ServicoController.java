package com.petshopapi.controller;

import com.petshopapi.dto.ServicoRequestDTO;
import com.petshopapi.dto.ServicoResponseDTO;
import com.petshopapi.service.ServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Servico", description = "Gerenciamento de servicos")
@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    @Autowired
    private ServicoService service;

    @Operation(summary = "Listar todos os Servico")
    @GetMapping
    public List<ServicoResponseDTO> listar(@RequestParam(required = false) String nome) {
        List<ServicoResponseDTO> resultado = service.listar();
        if (nome != null && !nome.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getNome() != null &&
                item.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @Operation(summary = "Buscar Servico por ID")
    @GetMapping("/{id}")
    public ServicoResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar Servico")
    @PostMapping
    public ResponseEntity<ServicoResponseDTO> criar(@Valid @RequestBody ServicoRequestDTO servico) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(servico));
    }

    @Operation(summary = "Atualizar Servico")
    @PutMapping("/{id}")
    public ServicoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ServicoRequestDTO servico) {
        return service.atualizar(id, servico);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir Servico")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
