package com.joao.clinicaveterinaria.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joao.clinicaveterinaria.dto.VeterinarioDto;
import com.joao.clinicaveterinaria.service.VeterinarioService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
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
	
	@GetMapping("/total")
	public long totalVeterinariosAtivo() {
		return veterinarioService.totalVeterinariosAtivo();
	}

	@GetMapping("/buscar")
	public List<VeterinarioDto> buscar(
	    @RequestParam(required = false) String nome,
	    @RequestParam(required = false) String especialidade,
	    @RequestParam(required = false) String status
	) {
	    return veterinarioService.buscar(nome, especialidade, status);
	}
	
	@PostMapping
	public VeterinarioDto criar(@Valid @RequestBody VeterinarioDto dto) {
		return veterinarioService.criar(dto);
	}
	
	@PutMapping("/{id}")
	public VeterinarioDto alterar(@PathVariable Long id, @RequestBody VeterinarioDto dto) {
		return veterinarioService.alterar(id, dto);
		
	}
	
	@PutMapping("/{id}/inativar")
	public void inativar(@PathVariable Long id) {
	    veterinarioService.inativar(id);
	}

	@PutMapping("/{id}/ativar")
	public void ativar(@PathVariable Long id) {
	    veterinarioService.ativar(id);
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		veterinarioService.excluir(id);
	}
	
}