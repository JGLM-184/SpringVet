package com.joao.clinicaveterinaria.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joao.clinicaveterinaria.service.AnimalService;

import jakarta.validation.Valid;

import com.joao.clinicaveterinaria.dto.AnimalDto;

@RestController
@RequestMapping("/animal")
public class AnimalController {
	
	private final AnimalService animalService;
	
	public AnimalController(AnimalService animalService) {
		this.animalService = animalService;
	}
	
	
	@GetMapping
	public List<AnimalDto> listar(){
		return animalService.listarTodos();
	}
			
	@GetMapping("/tutor/{id}")
	public List<AnimalDto> buscarPorTutor(@PathVariable Long id) {
		return animalService.buscarPorTutor(id);
	}
	
	@GetMapping("/{id}")
	public AnimalDto buscarPorId(@PathVariable Long id) {
		return animalService.buscarPorId(id);
	}
	
	@GetMapping("/buscar")
	public List<AnimalDto> buscarPorTutorENome(@RequestParam Long tutorId, @RequestParam String nome) {
	    return animalService.buscarPorNome(tutorId, nome);
	}
	
	@GetMapping("/total")
	public long totalAnimais() {
		return animalService.totalAnimais();
	}
	
	@PostMapping("/{id}")
	public AnimalDto salvar(@PathVariable Long id, @Valid @RequestBody AnimalDto dto) {
		return animalService.criar(dto, id);
	}
	
	@PutMapping("/{id}")
	public AnimalDto alterar(@PathVariable Long id, @Valid @RequestBody AnimalDto dto) {
	    return animalService.alterar(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
	    animalService.excluir(id);
	}
}
