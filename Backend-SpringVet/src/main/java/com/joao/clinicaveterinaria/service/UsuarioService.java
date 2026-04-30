package com.joao.clinicaveterinaria.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.joao.clinicaveterinaria.dto.UsuarioDto;
import com.joao.clinicaveterinaria.model.Usuario;
import com.joao.clinicaveterinaria.repository.UsuarioRepository;

@Service
public class UsuarioService {

private final UsuarioRepository usuarioRepository;
	
	public UsuarioService (UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public void  criar(UsuarioDto dto) {
		if(usuarioRepository.findByEmail(dto.getEmail()) == null) {
			
			String senhaCriptografada = new BCryptPasswordEncoder().encode(dto.getSenha() );  
			
			Usuario usuario = new Usuario();
			usuario.setEmail(dto.getEmail());
			usuario.setSenha(senhaCriptografada);
			usuario.setRole(dto.getRole());
			
			usuarioRepository.save(usuario);
		}		
	}
}
