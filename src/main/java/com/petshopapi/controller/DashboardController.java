package com.petshopapi.controller;

import com.petshopapi.model.Animal;
import com.petshopapi.model.Servico;
import com.petshopapi.model.Agendamento;
import com.petshopapi.model.Produto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@Tag(name = "Dashboard", description = "KPIs e totais do sistema")
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private com.petshopapi.repository.AnimalRepository animalRepository;

    @Autowired
    private com.petshopapi.repository.ServicoRepository servicoRepository;

    @Autowired
    private com.petshopapi.repository.AgendamentoRepository agendamentoRepository;

    @Autowired
    private com.petshopapi.repository.ProdutoRepository produtoRepository;

    @Operation(summary = "Resumo com totais, somas e gráficos de status")
    @Transactional(readOnly = true)
    @GetMapping("/resumo")
    public Map<String, Object> resumo() {
        Map<String, Object> resumo = new LinkedHashMap<>();
        resumo.put("totalAnimal", animalRepository.count());
        resumo.put("somaPesoAnimal", animalRepository.findAll().stream().filter(e -> e.getPeso() != null).mapToDouble(e -> e.getPeso()).sum());
        resumo.put("totalServico", servicoRepository.count());
        resumo.put("somaPrecoServico", servicoRepository.findAll().stream().filter(e -> e.getPreco() != null).mapToDouble(e -> e.getPreco()).sum());
        resumo.put("totalAgendamento", agendamentoRepository.count());
        resumo.put("somaValorAgendamento", agendamentoRepository.findAll().stream().filter(e -> e.getValor() != null).mapToDouble(e -> e.getValor()).sum());
        resumo.put("graficoAgendamento", agendamentoRepository.findAll().stream()
            .collect(java.util.stream.Collectors.groupingBy(
                item -> String.valueOf(item.getStatus()),
                java.util.LinkedHashMap::new,
                java.util.stream.Collectors.counting())));
        resumo.put("totalProduto", produtoRepository.count());
        resumo.put("somaPrecoProduto", produtoRepository.findAll().stream().filter(e -> e.getPreco() != null).mapToDouble(e -> e.getPreco()).sum());
        return resumo;
    }
}
