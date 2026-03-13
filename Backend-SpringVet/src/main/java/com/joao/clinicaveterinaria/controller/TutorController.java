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

import com.joao.clinicaveterinaria.dto.TutorDto;
import com.joao.clinicaveterinaria.service.TutorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tutor")
public class TutorController {
	
	private final TutorService tutorService;
	
	public TutorController (TutorService tutorService) {
		this.tutorService = tutorService;
	}
	

	@GetMapping()
	public List<TutorDto> listar() {
		return tutorService.listar();
	}
	
	@PostMapping
	public TutorDto salvar(@Valid @RequestBody TutorDto dto) {
		return tutorService.criar(dto);
	}
	
	@PutMapping("/{id}")
	public TutorDto alterar(@PathVariable Long id, @Valid @RequestBody TutorDto dto) {
		return tutorService.alterar(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		tutorService.excluir(id);
	}
	
}
