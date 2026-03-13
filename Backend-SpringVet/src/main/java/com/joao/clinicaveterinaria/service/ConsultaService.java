package com.joao.clinicaveterinaria.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.joao.clinicaveterinaria.dto.ConsultaDto;
import com.joao.clinicaveterinaria.exception.ResourceNotFoundException;
import com.joao.clinicaveterinaria.model.entity.Animal;
import com.joao.clinicaveterinaria.model.entity.Consulta;
import com.joao.clinicaveterinaria.model.entity.Tutor;
import com.joao.clinicaveterinaria.model.entity.Veterinario;
import com.joao.clinicaveterinaria.repository.AnimalRepository;
import com.joao.clinicaveterinaria.repository.ConsultaRepository;
import com.joao.clinicaveterinaria.repository.TutorRepository;
import com.joao.clinicaveterinaria.repository.VeterinarioRepository;

@Service
public class ConsultaService {

	private final ConsultaRepository consultaRepository;
	private final VeterinarioRepository veterinarioRepository;
	private final AnimalRepository animalRepository;
	private final TutorRepository tutorRepository;

			
	public ConsultaService(ConsultaRepository consultaRepository, VeterinarioRepository veterinarioRepository, 
			AnimalRepository animalRepository, TutorRepository tutorRepository) {
		this.consultaRepository = consultaRepository;
		this.veterinarioRepository = veterinarioRepository;
		this.animalRepository = animalRepository;
		this.tutorRepository = tutorRepository;

	}
	
	public List<ConsultaDto> listar() {
		List<Consulta> consultas = consultaRepository.findAll();
		List<ConsultaDto> consultasDto = new ArrayList<>();
		
		for (Consulta consulta : consultas) {
			ConsultaDto dto = toDto(consulta);
			consultasDto.add(dto);
		}
		
		return consultasDto;
	}
	
	public List<ConsultaDto> buscarPorTutor(Long id) {
		Tutor tutor = tutorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Tutor não encontrado"));
		
		List<Consulta> consultasTutor = consultaRepository.findByAnimalTutor(tutor);
		List<ConsultaDto> consultasTutorDto = new ArrayList<>();
		for (Consulta consulta : consultasTutor) {
			ConsultaDto dto = toDto(consulta);
			consultasTutorDto.add(dto);
		}
		
		return consultasTutorDto;
	}
	
	public List<ConsultaDto> buscarPorVeterinario(Long id) {
		Veterinario veterinario = veterinarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Veterinário não encontrado"));
		
		List<Consulta> consultasVeterinario = consultaRepository.findByVeterinario(veterinario);
		List<ConsultaDto> consultasVeterinarioDto = new ArrayList<>();
		for (Consulta consulta : consultasVeterinario) {
			ConsultaDto dto = toDto(consulta);
			consultasVeterinarioDto.add(dto);
		}
		
		return consultasVeterinarioDto;
	}
	
	public List<ConsultaDto> buscarPorAnimal(Long id) {
		Animal animal = animalRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Animal não encontrado"));
		
		List<Consulta> consultasAnimal = consultaRepository.findByAnimal(animal);
		List<ConsultaDto> consultasAnimalDto = new ArrayList<>();
		for (Consulta consulta : consultasAnimal) {
			ConsultaDto dto = toDto(consulta);
			consultasAnimalDto.add(dto);
		}
		
		return consultasAnimalDto;
	}

	public ConsultaDto criar(Long idAnimal, Long idVeterinario, ConsultaDto dto) {
		
		Consulta consulta = toConsulta(dto);
		
		Animal animal = animalRepository.findById(idAnimal)
				.orElseThrow(() -> new ResourceNotFoundException("Animal não encontrado"));
		
		Veterinario veterinario = veterinarioRepository.findById(idVeterinario)
				.orElseThrow(() -> new ResourceNotFoundException("Veterinario não encontrado"));
		
		consulta.setAnimal(animal);
		consulta.setVeterinario(veterinario);
		
		consultaRepository.save(consulta);
		
		return toDto(consulta);
	}
	
	public ConsultaDto alterar(Long id, Long idVeterinario, ConsultaDto dto) {
		Consulta consulta = consultaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada"));
		
		Veterinario veterinario = veterinarioRepository.findById(idVeterinario)
				.orElseThrow(() -> new ResourceNotFoundException("Veterinario não encontrado"));
		
		consulta.setDataHora(dto.getDataHora());
		consulta.setMotivo(dto.getMotivo());
		consulta.setObservacao(dto.getObservacao());
		consulta.setValor(dto.getValor());
		consulta.setFormaPagamento(dto.getFormaPagamento());
		consulta.setPaga(dto.isPaga());
		consulta.setVeterinario(veterinario);
		
		consultaRepository.save(consulta);
		
		return toDto(consulta);
	}
	
	public void excluir(Long id) {
		Consulta consulta = consultaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada"));
		
		consultaRepository.delete(consulta);
	}
	
	//------------------------------------------------------------------------------------------------------------
	private Consulta toConsulta(ConsultaDto dto) {
		Consulta consulta = new Consulta();
		
		consulta.setDataHora(dto.getDataHora());
		consulta.setStatus(dto.getStatus());
		consulta.setMotivo(dto.getMotivo());
		consulta.setObservacao(dto.getObservacao());
		consulta.setValor(dto.getValor());
		consulta.setPaga(dto.isPaga());
		consulta.setDataCriacao(dto.getDataCriacao());
		
		return consulta;
	}
	
	private ConsultaDto toDto(Consulta consulta) {
		ConsultaDto dto = new ConsultaDto();
		
		dto.setDataHora(consulta.getDataHora());
		dto.setStatus(consulta.getStatus());
		dto.setMotivo(consulta.getMotivo());
		dto.setObservacao(consulta.getObservacao());
		dto.setValor(consulta.getValor());
		dto.setPaga(consulta.isPaga());
		dto.setDataCriacao(consulta.getDataCriacao());
		dto.setAnimal(consulta.getAnimal());
		dto.setVeterinario(consulta.getVeterinario());
		
		return dto;
	}
	
}
