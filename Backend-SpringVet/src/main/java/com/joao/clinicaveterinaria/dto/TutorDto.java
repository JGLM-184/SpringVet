package com.joao.clinicaveterinaria.dto;

import java.util.List;
import jakarta.validation.constraints.NotBlank;

public class TutorDto {

	private Long id;
	@NotBlank(message="O nome é obrigatório")
	private String nome;
	@NotBlank(message="O CPF é obrigatório")
	private String cpf;
	@NotBlank(message="O telefone é obrigatório")
	private String telefone;
	@NotBlank(message="O e-mail é obrigatório")
	private String email;
	private List<AnimalDto> animais;
	
	public TutorDto() {
		
	}
	
	public TutorDto(Long id, String nome, String cpf, String telefone, String email, List<AnimalDto> animais) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.email = email;
		this.animais = animais;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<AnimalDto> getAnimais() {
		return animais;
	}

	public void setAnimais(List<AnimalDto> animais) {
		this.animais = animais;
	}
	
}
