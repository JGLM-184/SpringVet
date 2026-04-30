package com.joao.clinicaveterinaria.dto;

import com.joao.clinicaveterinaria.model.UserRole;

import jakarta.validation.constraints.NotBlank;

public class UsuarioDto {
	
	private Long id;
	@NotBlank
	private String email;
	@NotBlank
	private String senha;
	private UserRole role;
	
	public UsuarioDto() {
		
	}
	
	public UsuarioDto(Long id, String email, String senha, UserRole role) {
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String usuario) {
		this.email = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
}
