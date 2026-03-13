package com.joao.clinicaveterinaria.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joao.clinicaveterinaria.service.AnimalService;

import jakarta.validation.Valid;

import com.joao.clinicaveterinaria.dto.AnimalDto;

@RestController
@RequestMapping("/animal")
public class AnimalController {
	
	//INJEÇÃO DE DEPENDÊNCIA - SEM USAR AUTOWIRED
	private final AnimalService animalService;
	
	public AnimalController(AnimalService animalService) {
		this.animalService = animalService;
	}
	
	
	//APIS
	@GetMapping
	public List<AnimalDto> listar(){
		return animalService.listarTodos();
	}
	
	@GetMapping("/{id}")
	public AnimalDto buscarPorId(@PathVariable Long id) {
		return animalService.buscarPorId(id);
	}
	
	@GetMapping("/nome/{nome}")
	public AnimalDto buscarPorNome(@PathVariable String nome) {
		return animalService.buscarPorNome(nome);
	}
	
	@PostMapping("/{id}")
	public AnimalDto salvar(@PathVariable Long id, @Valid @RequestBody AnimalDto dto) {
		return animalService.criar(dto, id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AnimalDto> alterar(@PathVariable Long id, @Valid @RequestBody AnimalDto dto) {
	    AnimalDto animalDto = animalService.alterar(id, dto);
	    return ResponseEntity.ok(animalDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
	    animalService.excluir(id);
	    return ResponseEntity.noContent().build();
	}
}
