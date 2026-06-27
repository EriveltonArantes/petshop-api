package com.petshopapi.dto;

import jakarta.validation.constraints.*;

public class AnimalRequestDTO {

    @NotBlank(message = "nome não pode estar em branco")
    private String nome;

    private String especie;

    private String raca;
    @NotNull(message = "data nascimento não pode ser nulo")
    private java.time.LocalDateTime dataNascimento;
    @NotNull(message = "peso não pode ser nulo")
    private Double peso;
    @Positive(message = "cliente id deve ser um ID válido (positivo)")
    @NotNull(message = "cliente id não pode ser nulo")
    private Long clienteId;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }
    public String getRaca() { return raca; }
    public void setRaca(String raca) { this.raca = raca; }
    public java.time.LocalDateTime getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(java.time.LocalDateTime dataNascimento) { this.dataNascimento = dataNascimento; }
    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
}
