package com.joao.clinicaveterinaria.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ConsultaDto {

	private Long id;
	@FutureOrPresent (message = "Insira uma data válida")
	@NotNull(message = "A data é obrigatória")
	private LocalDateTime dataHora;
	private String status;
	@NotBlank(message = "O motivo é obrigatório")
	private String motivo;
	private String observacao;
	@NotNull(message = "O valor é obrigatório")
	private BigDecimal valor;
	@NotBlank(message = "A forma de pagamento é obrigatória")
	private String formaPagamento;
	private Boolean paga;
	private LocalDate dataCriacao;
	private Long animalId;
	private String animalNome;
	private Long veterinarioId;
	private String veterinarioNome;
	private String tutorNome;
	
	public ConsultaDto() {
		
	}

	public ConsultaDto(Long id, LocalDateTime dataHora, String status, String motivo, String observacao,
			BigDecimal valor, String formaPagamento, Boolean paga, LocalDate dataCriacao, Long animalId,
			String animalNome, Long veterinarioId, String veterinarioNome, String tutorNome) {
		this.id = id;
		this.dataHora = dataHora;
		this.status = status;
		this.motivo = motivo;
		this.observacao = observacao;
		this.valor = valor;
		this.formaPagamento = formaPagamento;
		this.paga = paga;
		this.dataCriacao = dataCriacao;
		this.animalId = animalId;
		this.animalNome = animalNome;
		this.veterinarioId = veterinarioId;
		this.veterinarioNome = veterinarioNome;
		this.tutorNome = tutorNome;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Boolean getPaga() {
		return paga;
	}

	public void setPaga(Boolean paga) {
		this.paga = paga;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Long getAnimalId() {
		return animalId;
	}

	public void setAnimalId(Long animalId) {
		this.animalId = animalId;
	}

	public String getAnimalNome() {
		return animalNome;
	}

	public void setAnimalNome(String animalNome) {
		this.animalNome = animalNome;
	}

	public Long getVeterinarioId() {
		return veterinarioId;
	}

	public void setVeterinarioId(Long veterinarioId) {
		this.veterinarioId = veterinarioId;
	}

	public String getVeterinarioNome() {
		return veterinarioNome;
	}

	public void setVeterinarioNome(String veterinarioNome) {
		this.veterinarioNome = veterinarioNome;
	}

	public String getTutorNome() {
		return tutorNome;
	}

	public void setTutorNome(String tutorNome) {
		this.tutorNome = tutorNome;
	}

	
}
