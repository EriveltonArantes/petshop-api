package com.petshopapi.controller;

import com.petshopapi.model.Produto;
import com.petshopapi.repository.ProdutoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@Tag(name = "Estoque", description = "Alertas de itens abaixo do estoque mínimo")
@RestController
@RequestMapping("/api/estoque")
public class EstoqueBaixoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    private java.util.List<Produto> produtoEstoqueBaixo() {
        return produtoRepository.findAll().stream()
            .filter(i -> i.getEstoque() != null && i.getEstoqueMinimo() != null && i.getEstoque() < i.getEstoqueMinimo())
            .collect(java.util.stream.Collectors.toList());
    }

    @Operation(summary = "Listar itens com estoque abaixo do mínimo")
    @Transactional(readOnly = true)
    @GetMapping("/baixo")
    public Map<String, Object> baixo() {
        Map<String, Object> r = new LinkedHashMap<>();
        r.put("Produto", produtoEstoqueBaixo());
        return r;
    }
}
