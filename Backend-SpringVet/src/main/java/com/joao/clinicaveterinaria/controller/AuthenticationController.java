package com.joao.clinicaveterinaria.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joao.clinicaveterinaria.config.TokenService;
import com.joao.clinicaveterinaria.dto.LoginResponseDto;
import com.joao.clinicaveterinaria.dto.UsuarioDto;
import com.joao.clinicaveterinaria.model.Usuario;
import com.joao.clinicaveterinaria.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	private final AuthenticationManager authenticationManager;
	private final UsuarioService usuarioService;
	private final TokenService tokenService;

	
	public AuthenticationController (AuthenticationManager authenticationManager, UsuarioService usuarioService
			,TokenService tokenService) {
		this.authenticationManager = authenticationManager;
		this.usuarioService = usuarioService;
		this.tokenService = tokenService;
	}
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid UsuarioDto dto) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		var token = tokenService.generateToken((Usuario) auth.getPrincipal());
		
		return ResponseEntity.ok(new LoginResponseDto(token));
	}
	
	@PostMapping("/criar")
	public void criar(@RequestBody @Valid UsuarioDto dto) {
		usuarioService.criar(dto);
	}
}
