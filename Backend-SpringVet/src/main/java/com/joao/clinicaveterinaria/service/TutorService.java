package com.joao.clinicaveterinaria.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.joao.clinicaveterinaria.dto.AnimalDto;
import com.joao.clinicaveterinaria.dto.TutorDto;
import com.joao.clinicaveterinaria.exception.ResourceNotFoundException;
import com.joao.clinicaveterinaria.model.entity.Animal;
import com.joao.clinicaveterinaria.model.entity.Tutor;
import com.joao.clinicaveterinaria.repository.AnimalRepository;
import com.joao.clinicaveterinaria.repository.TutorRepository;

@Service
public class TutorService {

	private final AnimalRepository animalRepository;
	private final TutorRepository tutorRepository;


	public TutorService(AnimalRepository animalRepository, TutorRepository tutorRepository) {
	    this.animalRepository = animalRepository;
	    this.tutorRepository = tutorRepository;
	}
	
	public List<TutorDto> listar() {
		
		List<Tutor> tutores = tutorRepository.findAll();
		List<TutorDto> tutoresDto = new ArrayList<>();

		for (Tutor tutor : tutores) {
			TutorDto dto = toDto(tutor);
			tutoresDto.add(dto);
		}
		
		return tutoresDto;
	}
	
	public List<TutorDto> buscarPorNome(String nome) {

	    List<Tutor> tutores = tutorRepository.findByNomeContainingIgnoreCase(nome);

	    List<TutorDto> tutoresDto = new ArrayList<>();

	    for (Tutor tutor : tutores) {
	        tutoresDto.add(toDto(tutor));
	    }

	    return tutoresDto;
	}
	
	public TutorDto buscarPorId(Long id) {
		
		Tutor tutor = tutorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Tutor não encontrado"));
		
		return toDto(tutor);
	}
	
	public TutorDto criar(TutorDto dto) {
		Tutor tutor = toTutor(dto);
		tutorRepository.save(tutor);
		
		if (dto.getAnimais() != null)
			salvarAnimais(dto.getAnimais(), tutor);
		 
		 return toDto(tutor);
	}
	
	public TutorDto alterar(Long id, TutorDto dto) {
		
		Tutor tutor = tutorRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Tutor não encontrado"));
		
		tutor.setNome(dto.getNome());
		tutor.setCpf(dto.getCpf());
		tutor.setTelefone(dto.getTelefone());
		tutor.setEmail(dto.getEmail());
		tutorRepository.save(tutor);
		
		return toDto(tutor);
	}
	
	public void excluir(Long id) {
		Tutor tutor = tutorRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Tutor não encontrado"));
		
		tutorRepository.delete(tutor);
	}
	
	
	//----------------------------------------------------------------------------------
	
	
	private Tutor toTutor (TutorDto dto) {
		Tutor tutor = new Tutor();
		tutor.setNome(dto.getNome());
		tutor.setCpf(dto.getCpf());
		tutor.setTelefone(dto.getTelefone());
		tutor.setEmail(dto.getEmail());

		return tutor;
	}
	
	private TutorDto toDto(Tutor tutor) {
		TutorDto dto = new TutorDto();
		dto.setId(tutor.getId());
		dto.setNome(tutor.getNome());
		dto.setCpf(tutor.getCpf());
		dto.setTelefone(tutor.getTelefone());
		dto.setEmail(tutor.getEmail());
		
		return dto;

	}
	
	private void salvarAnimais(List<AnimalDto> animaisDto, Tutor tutor) {
		for (AnimalDto animalDto : animaisDto) {
			Animal animal = new Animal();
			animal.setNome(animalDto.getNome());
			animal.setEspecie(animalDto.getEspecie());
			animal.setRaca(animalDto.getRaca());
			animal.setCor(animalDto.getCor());
			animal.setSexo(animalDto.getSexo());
			animal.setNasc(animalDto.getNasc());
			animal.setCastrado(animalDto.isCastrado());
			animal.setTutor(tutor);
			
			animalRepository.save(animal);
		}
	}
}
