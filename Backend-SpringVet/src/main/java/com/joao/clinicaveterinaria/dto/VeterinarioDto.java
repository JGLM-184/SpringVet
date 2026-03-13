package com.joao.clinicaveterinaria.dto;

import jakarta.validation.constraints.NotBlank;

public class VeterinarioDto{
	
	private Long id;
	@NotBlank(message = "O nome é obrigatório")
	private String nome;
	@NotBlank(message = "A especialidade é obrigatória")
	private String especialidade;
	@NotBlank(message = "O CRMV é obrigatório")
	private String crmv;
	@NotBlank(message = "O telefone é obrigatório")
	private String telefone;
	@NotBlank(message = "O email é obrigatório")
	private String email;
	private boolean ativo;
	
	public VeterinarioDto() {
		
	}
	
	public VeterinarioDto(Long id, String nome, String especialidade, String crmv, String telefone, String email, boolean ativo) {
		this.id = id;
		this.nome = nome;
		this.especialidade = especialidade;
		this.crmv = crmv;
		this.telefone = telefone;
		this.email = email;
		this.ativo = ativo;
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

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getCrmv() {
		return crmv;
	}

	public void setCrmv(String crmv) {
		this.crmv = crmv;
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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
	
}
