package com.joao.clinicaveterinaria.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.joao.clinicaveterinaria.dto.TutorDto;
import com.joao.clinicaveterinaria.dto.VeterinarioDto;
import com.joao.clinicaveterinaria.exception.ResourceNotFoundException;
import com.joao.clinicaveterinaria.model.entity.Tutor;
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
	
	public List<VeterinarioDto> buscar(String nome, String especialidade, String status) {

	    List<Veterinario> veterinarios = veterinarioRepository.findAll();
	    List<VeterinarioDto> resultado = new ArrayList<>();

	    for (Veterinario veterinario : veterinarios) {

	        boolean match = true;

	        // NOME
	        if (nome != null && !nome.isBlank()) {
	            if (!veterinario.getNome().toLowerCase().contains(nome.toLowerCase())) {
	                match = false;
	            }
	        }

	        // ESPECIALIDADE
	        if (especialidade != null && !especialidade.isBlank()) {
	            if (!veterinario.getEspecialidade().equalsIgnoreCase(especialidade)) {
	                match = false;
	            }
	        }

	        // STATUS (boolean)
	        if (status != null && !status.isBlank()) {

	            boolean ativoFiltro = status.equalsIgnoreCase("Ativo");

	            if (veterinario.isAtivo() != ativoFiltro) {
	                match = false;
	            }
	        }

	        if (match) {
	            resultado.add(toDto(veterinario));
	        }
	    }

	    return resultado;
	}

	public VeterinarioDto criar(VeterinarioDto dto) {
	    Veterinario veterinario = toVeterinario(dto);
	    
	    veterinario.setAtivo(true);
	    
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
		dto.setAtivo(veterinario.isAtivo());
		
		return dto;
	}
}
