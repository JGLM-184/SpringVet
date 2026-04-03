package com.joao.clinicaveterinaria.dto;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AnimalDto {

	private Long id;
	@NotBlank(message="O nome é obrigatório")
	private String nome;
	@NotBlank(message="A espécie é obrigatória")
	private String especie;
	@NotBlank(message="A raça é obrigatória")
	private String raca;
	@NotBlank(message="A cor é obrigatória")
	private String cor;
	@NotBlank(message="O sexo é obrigatório")
	private String sexo;
	@NotNull(message="A data de nascimento é obrigatória")
	private LocalDate nasc;
	@NotNull(message="A informação de castração é obrigatória")
	private boolean castrado;
	private Long tutorId;
	private String nomeTutor;
	
	public AnimalDto() {
		
	}

	public AnimalDto(Long id, String nome, String especie, String raca, String cor, String sexo, LocalDate nasc,
			boolean castrado, Long tutorId, String nomeTutor) {
		this.id = id;
		this.nome = nome;
		this.especie = especie;
		this.raca = raca;
		this.cor = cor;
		this.sexo = sexo;
		this.nasc = nasc;
		this.castrado = castrado;
		this.tutorId = tutorId;
		this.nomeTutor = nomeTutor;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public LocalDate getNasc() {
		return nasc;
	}

	public void setNasc(LocalDate nasc) {
		this.nasc = nasc;
	}

	public boolean isCastrado() {
		return castrado;
	}

	public void setCastrado(boolean castrado) {
		this.castrado = castrado;
	}

	public Long getTutorId() {
		return tutorId;
	}

	public void setTutorId(Long tutorId) {
		this.tutorId = tutorId;
	}

	public String getNomeTutor() {
		return nomeTutor;
	}

	public void setNomeTutor(String nomeTutor) {
		this.nomeTutor = nomeTutor;
	}

	
	
}
