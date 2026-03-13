package com.joao.clinicaveterinaria.model.entity;

public class User {

	private Long id;
	private String nome;
	private String username;
	private String password;
	private boolean ativo;
	
	public User() {
		
	}

	public User(Long id, String nome, String username, String password, boolean ativo) {
		this.id = id;
		this.nome = nome;
		this.username = username;
		this.password = password;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
	
}
