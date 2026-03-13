package com.joao.clinicaveterinaria.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.joao.clinicaveterinaria.dto.VeterinarioDto;
import com.joao.clinicaveterinaria.exception.ResourceNotFoundException;
import com.joao.clinicaveterinaria.model.entity.Veterinario;
import com.joao.clinicaveterinaria.repository.VeterinarioRepository;

@Service
public class VeterinarioService {

	private final VeterinarioRepository veterinarioRepository;
	
	public VeterinarioService (VeterinarioRepository veterinarioRepository) {
		this.veterinarioRepository = veterinarioRepository;
	}
	
	public List<VeterinarioDto> listar() {
		List<Veterinario> veterinarios = veterinarioRepository.findAll();
		List<VeterinarioDto> veterinariosDto = new ArrayList<>();
		
		for (Veterinario veterinario : veterinarios) {
			VeterinarioDto dto = toDto(veterinario);
			veterinariosDto.add(dto);
		}
		
		return veterinariosDto;
	}
	
	public VeterinarioDto buscarPorId(Long id) {
		Veterinario veterinario = veterinarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Veterinário não encontrado"));
				
		return toDto(veterinario);		
	}
	
	public VeterinarioDto buscarPorNome(String nome) {
		Veterinario veterinario = veterinarioRepository.findByNome(nome)
				.orElseThrow(() -> new ResourceNotFoundException("Veterinário não encontrado"));
				
		return toDto(veterinario);		
	}

	public VeterinarioDto criar(VeterinarioDto dto) {
	    Veterinario veterinario = toVeterinario(dto);
	    
	    veterinarioRepository.save(veterinario);
	    
	    return toDto(veterinario);
	}
	
	public VeterinarioDto alterar(Long id, VeterinarioDto dto) {
		Veterinario veterinario = veterinarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Veterinário não encontrado"));
		
		veterinario.setNome(dto.getNome());
		veterinario.setEspecialidade(dto.getEspecialidade());
		veterinario.setCrmv(dto.getCrmv());
		veterinario.setTelefone(dto.getTelefone());
		veterinario.setEmail(dto.getEmail());
		
		veterinarioRepository.save(veterinario);
		
		return toDto(veterinario);
	}
	
	public void excluir(Long id) {
		Veterinario veterinario = veterinarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Veterinário não encontrado"));
		
		veterinarioRepository.delete(veterinario);
	}
	
	public void inativar (Long id) {
		Veterinario veterinario = veterinarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Veterinário não encontrado"));
		
		veterinario.setAtivo(false);
		veterinarioRepository.save(veterinario);
	}
	
	public void ativar (Long id) {
		Veterinario veterinario = veterinarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Veterinário não encontrado"));
		
		veterinario.setAtivo(true);
		veterinarioRepository.save(veterinario);
	}
	
	//----------------------------------------------
	private Veterinario toVeterinario(VeterinarioDto dto ) {
		Veterinario veterinario = new Veterinario();
	
		veterinario.setNome(dto.getNome());
		veterinario.setEspecialidade(dto.getEspecialidade());
		veterinario.setCrmv(dto.getCrmv());
		veterinario.setTelefone(dto.getTelefone());
		veterinario.setEmail(dto.getEmail());
		veterinario.setAtivo(true);
		
		return veterinario;
	}
	
	private VeterinarioDto toDto(Veterinario veterinario) {
		VeterinarioDto dto = new VeterinarioDto();
		dto.setId(veterinario.getId());
		dto.setNome(veterinario.getNome());
		dto.setEspecialidade(veterinario.getEspecialidade());
		dto.setCrmv(veterinario.getCrmv());
		dto.setTelefone(veterinario.getTelefone());
		dto.setEmail(veterinario.getEmail());
		
		return dto;
	}
}
