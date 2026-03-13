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

import com.joao.clinicaveterinaria.dto.ConsultaDto;
import com.joao.clinicaveterinaria.service.ConsultaService;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

	private final ConsultaService consultaService;

	public ConsultaController(ConsultaService consultaService) {
		this.consultaService = consultaService;
	}
	
	@GetMapping
	public List<ConsultaDto> listarConsultas() {
		return consultaService.listar();
	}
	
	@GetMapping("/tutor/{id}")
	public List<ConsultaDto> buscarPorTutor(@PathVariable Long id) {
		return consultaService.buscarPorTutor(id);
	}
	
	@GetMapping("/animal/{id}")
	public List<ConsultaDto> buscarPorAnimal(@PathVariable Long id) {
		return consultaService.buscarPorAnimal(id);
	}
	
	@GetMapping("/veterinario/{id}")
	public List<ConsultaDto> buscarPorVeterinario(@PathVariable Long id) {
		return consultaService.buscarPorVeterinario(id);
	}
	
	@PostMapping("/animal/{idAnimal}/veterinario/{idVeterinario}")
	public ConsultaDto criar(@PathVariable Long idAnimal, @PathVariable Long idVeterinario, @RequestBody ConsultaDto dto) {
		return consultaService.criar(idAnimal, idVeterinario, dto);
	}
	
	@PutMapping("/{id}/veterinario/{idVeterinario}")
	public ConsultaDto alterar(@PathVariable Long id, @PathVariable Long idVeterinario, @RequestBody ConsultaDto dto) {
		return consultaService.alterar(id, idVeterinario, dto);
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		consultaService.excluir(id);
	}
}
