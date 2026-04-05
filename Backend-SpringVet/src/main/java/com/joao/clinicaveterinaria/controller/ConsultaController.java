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
import org.springframework.web.bind.annotation.RestController;

import com.joao.clinicaveterinaria.dto.ConsultaDetalheDto;
import com.joao.clinicaveterinaria.dto.ConsultaDto;
import com.joao.clinicaveterinaria.service.ConsultaService;

import jakarta.validation.Valid;

@CrossOrigin (origins = "*")
@RestController
@RequestMapping("/consultas")
public class ConsultaController {

	private final ConsultaService consultaService;

	public ConsultaController(ConsultaService consultaService) {
		this.consultaService = consultaService;
	}
	
	@GetMapping
	public List<ConsultaDto> listarConsultas() {
		return consultaService.listar();
	}
	
	@GetMapping("/{id}")
	public ConsultaDetalheDto buscarPorId(@PathVariable Long id) {
		return consultaService.buscarPorId(id);
	}
	
	@GetMapping("total")
	public int totalConsulta() {
		return consultaService.totalAgendada();
	}
	
	@GetMapping("/hoje")
	public List<ConsultaDto> ConsultasDiarias() {
		return consultaService.consultasHoje();
	}
	
	@GetMapping("total/hoje")
	public int totalConsultaHoje() {
		return consultaService.totalHoje();
	}
	
	@GetMapping("atrasadas")
	public List<ConsultaDto> consultasAtrasadas() {
		return consultaService.buscarAtrasadas();
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
	public ConsultaDto criar(@PathVariable Long idAnimal, @PathVariable Long idVeterinario, @Valid @RequestBody ConsultaDto dto) {
		return consultaService.criar(idAnimal, idVeterinario, dto);
	}
	
	@PutMapping("/{id}/veterinario/{idVeterinario}")
	public ConsultaDetalheDto alterar(@PathVariable Long id, @PathVariable Long idVeterinario, @Valid @RequestBody ConsultaDto dto) {
		return consultaService.alterar(id, idVeterinario, dto);
	}
	
	@PutMapping("/{id}/cancelar")
	public void cancelarConsulta(@PathVariable Long id) {
		consultaService.cancelarConsulta(id);
	}
	
	@PutMapping("/{id}/finalizar")
	public void finalizarConsulta(@PathVariable Long id) {
		consultaService.finalizarConsulta(id);
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		consultaService.excluir(id);
	}
}
