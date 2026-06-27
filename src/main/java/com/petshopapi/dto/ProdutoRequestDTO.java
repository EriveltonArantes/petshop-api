package com.petshopapi.dto;

import jakarta.validation.constraints.*;

public class ProdutoRequestDTO {

    @NotBlank(message = "nome não pode estar em branco")
    private String nome;
    @NotBlank(message = "categoria não pode estar em branco")
    private String categoria;
    @DecimalMin(value = "0.0", message = "preco não pode ser negativo")
    @NotNull(message = "preco não pode ser nulo")
    private Double preco;
    @Min(value = 0, message = "estoque não pode ser negativo")
    @NotNull(message = "estoque não pode ser nulo")
    private Integer estoque;
    @Min(value = 0, message = "estoque minimo não pode ser negativo")
    @NotNull(message = "estoque minimo não pode ser nulo")
    private Integer estoqueMinimo;
    @NotBlank(message = "fornecedor não pode estar em branco")
    private String fornecedor;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
    public Integer getEstoque() { return estoque; }
    public void setEstoque(Integer estoque) { this.estoque = estoque; }
    public Integer getEstoqueMinimo() { return estoqueMinimo; }
    public void setEstoqueMinimo(Integer estoqueMinimo) { this.estoqueMinimo = estoqueMinimo; }
    public String getFornecedor() { return fornecedor; }
    public void setFornecedor(String fornecedor) { this.fornecedor = fornecedor; }
}
