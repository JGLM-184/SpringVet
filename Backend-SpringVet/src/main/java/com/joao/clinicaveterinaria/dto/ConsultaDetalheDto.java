package com.joao.clinicaveterinaria.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;



public class ConsultaDetalheDto {

	private Long id;
	private LocalDateTime dataHora;
	private String status;
	private String motivo;
	private String observacao;
	private BigDecimal valor;
	private String formaPagamento;
	private Boolean paga;
	private LocalDate dataCriacao;
	
	private Long animalId;
	
	private String animalNome;
	private String animalEspecie;
	private String animalRaca;
	private String animalCor;
	private String animalSexo;
	private LocalDate animalNasc;
	private boolean animalCastrado;
	
	private Long veterinarioId;
	private String veterinarioNome;
	private String veterinarioEspecialidade;
	private String veterinarioCrmv;
	private String veterinarioTelefone;
	private String veterinarioEmail;
	
	private String tutorNome;
	
	public ConsultaDetalheDto() {
	}

	public ConsultaDetalheDto(Long id, LocalDateTime dataHora, String status, String motivo, String observacao,
			BigDecimal valor, String formaPagamento, Boolean paga, LocalDate dataCriacao, Long animalId,
			String animalNome, String animalEspecie, String animalRaca, String animalCor, String animalSexo,
			LocalDate animalNasc, boolean animalCastrado, Long veterinarioId, String veterinarioNome,
			String veterinarioEspecialidade, String veterinarioCrmv, String veterinarioTelefone,
			String veterinarioEmail, String tutorNome) {
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
		this.animalEspecie = animalEspecie;
		this.animalRaca = animalRaca;
		this.animalCor = animalCor;
		this.animalSexo = animalSexo;
		this.animalNasc = animalNasc;
		this.animalCastrado = animalCastrado;
		this.veterinarioId = veterinarioId;
		this.veterinarioNome = veterinarioNome;
		this.veterinarioEspecialidade = veterinarioEspecialidade;
		this.veterinarioCrmv = veterinarioCrmv;
		this.veterinarioTelefone = veterinarioTelefone;
		this.veterinarioEmail = veterinarioEmail;
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

	public String getAnimalEspecie() {
		return animalEspecie;
	}

	public void setAnimalEspecie(String animalEspecie) {
		this.animalEspecie = animalEspecie;
	}

	public String getAnimalRaca() {
		return animalRaca;
	}

	public void setAnimalRaca(String animalRaca) {
		this.animalRaca = animalRaca;
	}

	public String getAnimalCor() {
		return animalCor;
	}

	public void setAnimalCor(String animalCor) {
		this.animalCor = animalCor;
	}

	public String getAnimalSexo() {
		return animalSexo;
	}

	public void setAnimalSexo(String animalSexo) {
		this.animalSexo = animalSexo;
	}

	public LocalDate getAnimalNasc() {
		return animalNasc;
	}

	public void setAnimalNasc(LocalDate animalNasc) {
		this.animalNasc = animalNasc;
	}

	public boolean isAnimalCastrado() {
		return animalCastrado;
	}

	public void setAnimalCastrado(boolean animalCastrado) {
		this.animalCastrado = animalCastrado;
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

	public String getVeterinarioEspecialidade() {
		return veterinarioEspecialidade;
	}

	public void setVeterinarioEspecialidade(String veterinarioEspecialidade) {
		this.veterinarioEspecialidade = veterinarioEspecialidade;
	}

	public String getVeterinarioCrmv() {
		return veterinarioCrmv;
	}

	public void setVeterinarioCrmv(String veterinarioCrmv) {
		this.veterinarioCrmv = veterinarioCrmv;
	}

	public String getVeterinarioTelefone() {
		return veterinarioTelefone;
	}

	public void setVeterinarioTelefone(String veterinarioTelefone) {
		this.veterinarioTelefone = veterinarioTelefone;
	}

	public String getVeterinarioEmail() {
		return veterinarioEmail;
	}

	public void setVeterinarioEmail(String veterinarioEmail) {
		this.veterinarioEmail = veterinarioEmail;
	}

	public String getTutorNome() {
		return tutorNome;
	}

	public void setTutorNome(String tutorNome) {
		this.tutorNome = tutorNome;
	}
	
}
