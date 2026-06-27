package com.petshopapi.dto;

public class AgendamentoResponseDTO {

    private Long id;
    private Long animalId;
    private Long servicoId;
    private java.time.LocalDateTime dataHora;
    private String status;
    private String observacoes;
    private Double valor;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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
