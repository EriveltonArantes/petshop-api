package com.petshopapi.controller;

import com.petshopapi.dto.AnimalRequestDTO;
import com.petshopapi.dto.AnimalResponseDTO;
import com.petshopapi.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Animal", description = "Gerenciamento de animals")
@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    @Autowired
    private AnimalService service;

    @Operation(summary = "Listar todos os Animal")
    @GetMapping
    public List<AnimalResponseDTO> listar(@RequestParam(required = false) String nome) {
        List<AnimalResponseDTO> resultado = service.listar();
        if (nome != null && !nome.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getNome() != null &&
                item.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @Operation(summary = "Buscar Animal por ID")
    @GetMapping("/{id}")
    public AnimalResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar Animal")
    @PostMapping
    public ResponseEntity<AnimalResponseDTO> criar(@Valid @RequestBody AnimalRequestDTO animal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(animal));
    }

    @Operation(summary = "Atualizar Animal")
    @PutMapping("/{id}")
    public AnimalResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody AnimalRequestDTO animal) {
        return service.atualizar(id, animal);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir Animal")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
