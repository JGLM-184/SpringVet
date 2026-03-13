package com.joao.clinicaveterinaria.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joao.clinicaveterinaria.dto.VeterinarioDto;
import com.joao.clinicaveterinaria.service.VeterinarioService;

@RestController
@RequestMapping("/veterinario")
public class VeterinarioController {
	
	private final VeterinarioService veterinarioService;

	
	public VeterinarioController(VeterinarioService veterinarioService) {
		this.veterinarioService = veterinarioService;
	}


	@GetMapping
	public List<VeterinarioDto> listar() {
		return veterinarioService.listar();
	}
	
	@GetMapping("/{id}")
	public VeterinarioDto buscarPorId(@PathVariable Long id) {
		return veterinarioService.buscarPorId(id);
	}
	
	@GetMapping("/nome/{nome}")
	public VeterinarioDto buscarPorNome(@PathVariable String nome) {
		return veterinarioService.buscarPorNome(nome);
	}
	
	@PostMapping
	public VeterinarioDto criar(@RequestBody VeterinarioDto dto) {
		return veterinarioService.criar(dto);
	}
	
	@PutMapping("/{id}")
	public VeterinarioDto alterar(@PathVariable Long id, @RequestBody VeterinarioDto dto) {
		return veterinarioService.alterar(id, dto);
		
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		veterinarioService.excluir(id);
	}
	
}