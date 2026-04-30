package com.joao.clinicaveterinaria.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "consultas")
public class Consulta {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime dataHora;
	private String status;
	private String motivo;
	private String observacao;
	private BigDecimal valor;
	private String formaPagamento;
	private Boolean paga;
	private LocalDate dataCriacao;
	@ManyToOne
	@JoinColumn(name = "animal_id")
	private Animal animal;
	@ManyToOne
	@JoinColumn(name = "veterinario_id")
	private Veterinario veterinario;
	
	public Consulta() {
		
	}
	
	public Consulta(Long id, LocalDateTime dataHora, String status, String motivo, String observacao, BigDecimal valor,
			String formaPagamento, Boolean paga, LocalDate dataCriacao, Animal animal, Veterinario veterinario) {
		this.id = id;
		this.dataHora = dataHora;
		this.status = status;
		this.motivo = motivo;
		this.observacao = observacao;
		this.valor = valor;
		this.formaPagamento = formaPagamento;
		this.paga = paga;
		this.dataCriacao = dataCriacao;
		this.animal = animal;
		this.veterinario = veterinario;
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

	public Boolean isPaga() {
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

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Veterinario getVeterinario() {
		return veterinario;
	}

	public void setVeterinario(Veterinario veterinario) {
		this.veterinario = veterinario;
	}
	
}
