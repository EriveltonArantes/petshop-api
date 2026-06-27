package com.petshopapi.dto;

import jakarta.validation.constraints.*;

public class AgendamentoRequestDTO {

    @NotNull(message = "AnimalId é obrigatório")
    @Positive(message = "AnimalId deve ser um ID válido (positivo)")
    private Long animalId;
    @NotNull(message = "ServicoId é obrigatório")
    @Positive(message = "ServicoId deve ser um ID válido (positivo)")
    private Long servicoId;
    @FutureOrPresent(message = "data hora não pode ser retroativo")
    @NotNull(message = "data hora não pode ser nulo")
    private java.time.LocalDateTime dataHora;
    @NotBlank(message = "status não pode estar em branco")
    private String status;

    private String observacoes;
    @DecimalMin(value = "0.0", message = "valor não pode ser negativo")
    @NotNull(message = "valor não pode ser nulo")
    private Double valor;

    public Long getAnimalId() { return animalId; }
    public void setAnimalId(Long animalId) { this.animalId = animalId; }
    public Long getServicoId() { return servicoId; }
    public void setServicoId(Long servicoId) { this.servicoId = servicoId; }
    public java.time.LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(java.time.LocalDateTime dataHora) { this.dataHora = dataHora; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }
}
