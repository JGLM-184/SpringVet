package com.joao.clinicaveterinaria.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.joao.clinicaveterinaria.dto.ConsultaDetalheDto;
import com.joao.clinicaveterinaria.dto.ConsultaDto;
import com.joao.clinicaveterinaria.exception.BusinessException;
import com.joao.clinicaveterinaria.exception.ResourceNotFoundException;
import com.joao.clinicaveterinaria.model.entity.Animal;
import com.joao.clinicaveterinaria.model.entity.Consulta;
import com.joao.clinicaveterinaria.model.entity.Tutor;
import com.joao.clinicaveterinaria.model.entity.Veterinario;
import com.joao.clinicaveterinaria.repository.AnimalRepository;
import com.joao.clinicaveterinaria.repository.ConsultaRepository;
import com.joao.clinicaveterinaria.repository.VeterinarioRepository;

@Service
public class ConsultaService {

	private final ConsultaRepository consultaRepository;
	private final VeterinarioRepository veterinarioRepository;
	private final AnimalRepository animalRepository;

			
	public ConsultaService(ConsultaRepository consultaRepository, VeterinarioRepository veterinarioRepository, 
			AnimalRepository animalRepository) {
		this.consultaRepository = consultaRepository;
		this.veterinarioRepository = veterinarioRepository;
		this.animalRepository = animalRepository;

	}
	
	
	
	// CONSULTAS / GET

	public List<ConsultaDto> listar() {
		List<Consulta> consultas = consultaRepository.findAll();
		List<ConsultaDto> consultasDto = new ArrayList<>();
		
		for (Consulta consulta : consultas) {
			ConsultaDto dto = toDto(consulta);
			consultasDto.add(dto);
		}
		
		return consultasDto;
	}
	
	public ConsultaDetalheDto buscarPorId(Long id) {
		Consulta consulta = consultaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada"));
		
		return toDtoDetalhe(consulta); 
	}
	
	public List<ConsultaDto> consultasHoje() {
		
		LocalDate hoje = LocalDate.now();

		LocalDateTime inicio = hoje.atStartOfDay();
		LocalDateTime fim = hoje.atTime(23, 59, 59);

		List<Consulta> consultas = consultaRepository.findByDataHoraBetween(inicio, fim);
		List<ConsultaDto> consultasDto = new ArrayList<>();
		
		for (Consulta consulta : consultas) {
				ConsultaDto dto = toDto(consulta);
				consultasDto.add(dto);
			}
		
		return consultasDto;
	}
	
	public int totalAgendada() {
		return consultaRepository.countByStatus("AGENDADA");
	}
	
	public int totalHoje() {
		LocalDate hoje = LocalDate.now();

		LocalDateTime inicio = hoje.atStartOfDay();
		LocalDateTime fim = hoje.atTime(23, 59, 59);

		List<Consulta> consultas = consultaRepository.findByDataHoraBetween(inicio, fim);
		
		return consultas.size();
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
	
	public List<ConsultaDto> buscarAtrasadas() {
	    List<Consulta> consultas = consultaRepository.findByDataHoraBeforeAndStatus(LocalDateTime.now(), "AGENDADA");
	    List<ConsultaDto> consultasDto = new ArrayList<>();
	    
	    for (Consulta consulta : consultas) {
	    	ConsultaDto dto = toDto(consulta);
			consultasDto.add(dto);
		}
	    
	    return consultasDto;
	}
	
	//-------------------------------

	public ConsultaDto criar(Long idAnimal, Long idVeterinario, ConsultaDto dto) {		
		Animal animal = animalRepository.findById(idAnimal)
				.orElseThrow(() -> new ResourceNotFoundException("Animal não encontrado"));
		
		Veterinario veterinario = veterinarioRepository.findById(idVeterinario)
				.orElseThrow(() -> new ResourceNotFoundException("Veterinario não encontrado"));
		
		if (consultaRepository.existsByVeterinarioAndDataHora(veterinario, dto.getDataHora())) {
		    throw new BusinessException("Já existe uma consulta para esse veterinário nesse horário");
		}
		
		Consulta consulta = toConsulta(dto);

		
		consulta.setAnimal(animal);
		consulta.setVeterinario(veterinario);
		
		
		consultaRepository.save(consulta);
		
		return toDto(consulta);
	}
	
	public ConsultaDetalheDto alterar(Long id, Long idVeterinario, ConsultaDto dto) {

	    Consulta consulta = consultaRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada"));

	    Veterinario veterinario = veterinarioRepository.findById(idVeterinario)
	            .orElseThrow(() -> new ResourceNotFoundException("Veterinario não encontrado"));

	    boolean mudouHorario = !consulta.getDataHora().equals(dto.getDataHora());
	    boolean mudouVeterinario = !consulta.getVeterinario().getId().equals(idVeterinario);

	    if (mudouHorario || mudouVeterinario) {

	        if (consultaRepository.existsByVeterinarioAndDataHora(veterinario, dto.getDataHora())) {

	            List<Consulta> consultasMesmoHorario = consultaRepository.findByVeterinario(veterinario);

	            boolean conflito = consultasMesmoHorario.stream()
	                    .anyMatch(c ->
	                            c.getDataHora().equals(dto.getDataHora()) &&
	                            !c.getId().equals(consulta.getId())
	                    );

	            if (conflito) {
	                throw new BusinessException("Já existe uma consulta para esse veterinário nesse horário");
	            }
	        }
	    }

	    consulta.setDataHora(dto.getDataHora());
	    consulta.setMotivo(dto.getMotivo());
	    consulta.setObservacao(dto.getObservacao());
	    consulta.setValor(dto.getValor());
	    consulta.setFormaPagamento(dto.getFormaPagamento());
	    consulta.setPaga(dto.getPaga());
	    consulta.setVeterinario(veterinario);

	    consultaRepository.save(consulta);

	    return toDtoDetalhe(consulta);
	}
	
	public void cancelarConsulta(Long id) {
		Consulta consulta = consultaRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada"));
		
		consulta.setStatus("CANCELADA");
		
		consultaRepository.save(consulta);
		
	}
	
	public void finalizarConsulta(Long id) {
		Consulta consulta = consultaRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada"));
		
		consulta.setStatus("FINALIZADA");
		consulta.setPaga(true);
		consultaRepository.save(consulta);
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
		consulta.setStatus("AGENDADA");
		consulta.setMotivo(dto.getMotivo());
		consulta.setObservacao(dto.getObservacao());
		consulta.setValor(dto.getValor());
		consulta.setFormaPagamento(dto.getFormaPagamento());
		consulta.setPaga(false);
		consulta.setDataCriacao(LocalDate.now());
		
		return consulta;
	}
	
	private ConsultaDto toDto(Consulta consulta) {
		ConsultaDto dto = new ConsultaDto();
		
		Animal animal = consulta.getAnimal();
		Veterinario veterinario = consulta.getVeterinario();
		Tutor tutor = animal.getTutor();
		
		dto.setId(consulta.getId());
		dto.setDataHora(consulta.getDataHora());
		dto.setStatus(consulta.getStatus());
		dto.setMotivo(consulta.getMotivo());
		dto.setObservacao(consulta.getObservacao());
		dto.setValor(consulta.getValor());
		dto.setFormaPagamento(consulta.getFormaPagamento());
		dto.setPaga(consulta.isPaga());
		dto.setDataCriacao(consulta.getDataCriacao());
		dto.setAnimalId(animal.getId());
		dto.setAnimalNome(animal.getNome());
		dto.setVeterinarioId(veterinario.getId());
		dto.setVeterinarioNome(veterinario.getNome());
		dto.setTutorNome(tutor.getNome());

		
		return dto;
	}
	
	private ConsultaDetalheDto toDtoDetalhe(Consulta consulta) {
		ConsultaDetalheDto dtoDetalhe = new ConsultaDetalheDto();
		
		Animal animal = consulta.getAnimal();
		Veterinario veterinario = consulta.getVeterinario();
		Tutor tutor = animal.getTutor();
		
		dtoDetalhe.setId(consulta.getId());
		dtoDetalhe.setDataHora(consulta.getDataHora());
		dtoDetalhe.setStatus(consulta.getStatus());
		dtoDetalhe.setMotivo(consulta.getMotivo());
		dtoDetalhe.setObservacao(consulta.getObservacao());
		dtoDetalhe.setValor(consulta.getValor());
		dtoDetalhe.setFormaPagamento(consulta.getFormaPagamento());
		dtoDetalhe.setPaga(consulta.isPaga());
		dtoDetalhe.setDataCriacao(consulta.getDataCriacao());
		
		dtoDetalhe.setAnimalId(animal.getId());
		dtoDetalhe.setAnimalNome(animal.getNome());
		dtoDetalhe.setAnimalEspecie(animal.getEspecie());
		dtoDetalhe.setAnimalRaca(animal.getRaca());
		dtoDetalhe.setAnimalCor(animal.getCor());
		dtoDetalhe.setAnimalSexo(animal.getSexo());
		dtoDetalhe.setAnimalNasc(animal.getNasc());
		dtoDetalhe.setAnimalCastrado(animal.isCastrado());
		
		dtoDetalhe.setVeterinarioId(veterinario.getId());
		dtoDetalhe.setVeterinarioNome(veterinario.getNome());
		dtoDetalhe.setVeterinarioEspecialidade(veterinario.getEspecialidade());
		dtoDetalhe.setVeterinarioCrmv(veterinario.getCrmv());
		dtoDetalhe.setVeterinarioTelefone(veterinario.getTelefone());
		dtoDetalhe.setVeterinarioEmail(veterinario.getEmail());
		
		dtoDetalhe.setTutorNome(tutor.getNome());

		
		return dtoDetalhe;
	}
	
}
