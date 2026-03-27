package com.joao.clinicaveterinaria.service;

import java.util.ArrayList;
import java.util.List;
	
import org.springframework.stereotype.Service;

import com.joao.clinicaveterinaria.dto.AnimalDto;
import com.joao.clinicaveterinaria.exception.ResourceNotFoundException;
import com.joao.clinicaveterinaria.model.entity.Animal;
import com.joao.clinicaveterinaria.model.entity.Tutor;
import com.joao.clinicaveterinaria.repository.AnimalRepository;
import com.joao.clinicaveterinaria.repository.TutorRepository;

@Service
public class AnimalService {

	private final AnimalRepository animalRepository;
	private final TutorRepository tutorRepository;


	public AnimalService(AnimalRepository animalRepository, TutorRepository tutorRepository) {
	    this.animalRepository = animalRepository;
	    this.tutorRepository = tutorRepository;

	}
	
	
	public List<AnimalDto> listarTodos (){
		List<Animal> animais = animalRepository.findAll();
		List<AnimalDto> animaisDto = new ArrayList<>();

		for (Animal animal : animais) {
			AnimalDto dto = toDto(animal);
			
			animaisDto.add(dto);
		}
		
		return animaisDto;
	}
	
	
	public List<AnimalDto> buscarPorTutor(Long id) {
		List<Animal> animais = animalRepository.findByTutorId(id);
		List<AnimalDto> animaisDto = new ArrayList<>();
		for (Animal animal : animais) {
			animaisDto.add(toDto(animal));
		}
		
		return animaisDto;
		
	}
	
	public AnimalDto buscarPorId(Long id) {
		Animal animal = animalRepository.findById(id)	
				.orElseThrow(() -> new ResourceNotFoundException("Animal não encontrado"));
				
		return toDto(animal);
	}	
	
	public List<AnimalDto> buscarPorNome(Long id, String nome) {
		List<Animal> animais = animalRepository.findByTutorIdAndNomeContainingIgnoreCase(id, nome);

	    List<AnimalDto> animaisDto = new ArrayList<>();

	    for (Animal animal : animais) {
	    	animaisDto.add(toDto(animal));
	    }

	    return animaisDto;
	}
	
	public AnimalDto criar(AnimalDto dto, Long id) {
		Tutor tutor = tutorRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Tutor não encontrado"));
		
		Animal animal = toAnimal(dto);
		animal.setTutor(tutor);
		
		animalRepository.save(animal);
		
		return toDto(animal);
	}
	
	public AnimalDto alterar(Long id, AnimalDto dto) {
	    Animal animal = animalRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Animal não encontrado"));

	    animal.setNome(dto.getNome());
	    animal.setEspecie(dto.getEspecie());
	    animal.setRaca(dto.getRaca());
	    animal.setCor(dto.getCor());
	    animal.setSexo(dto.getSexo());
	    animal.setNasc(dto.getNasc());
	    animal.setCastrado(dto.isCastrado());

	    animalRepository.save(animal);
	    
	    return toDto(animal);
	}
	
	public void excluir(Long id) {
		Animal animal = animalRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Animal não encontrado"));
		
			animalRepository.delete(animal);
	}

	public Animal associarTutor(Long idTutor, Long idAnimal) {
		Tutor tutor = tutorRepository.findById(idTutor).
				orElseThrow(() -> new ResourceNotFoundException("Tutor não encontrado"));
		
		Animal animal = animalRepository.findById(idAnimal).
				orElseThrow(() -> new ResourceNotFoundException("Animal não encontrado"));
		
		animal.setTutor(tutor);
		
		return animalRepository.save(animal);
		
	}
	
	//------------------------------------------------------
	
	public Animal toAnimal (AnimalDto dto) {
		
		Animal animal = new Animal();
		animal.setNome(dto.getNome());
		animal.setEspecie(dto.getEspecie());
		animal.setRaca(dto.getRaca());
		animal.setCor(dto.getCor());
		animal.setSexo(dto.getSexo());
		animal.setNasc(dto.getNasc());
		animal.setCastrado(dto.isCastrado());
		
		return animal;
	}
	
	public AnimalDto toDto (Animal animal) {
		
		AnimalDto dto = new AnimalDto();
		dto.setId(animal.getId());
		dto.setNome(animal.getNome());
		dto.setEspecie(animal.getEspecie());
		dto.setRaca(animal.getRaca());
		dto.setCor(animal.getCor());
		dto.setSexo(animal.getSexo());
		dto.setNasc(animal.getNasc());
		dto.setCastrado(animal.isCastrado());

		if (animal.getTutor() != null) {
	        dto.setTutorId(animal.getTutor().getId());
	        dto.setNomeTutor(animal.getTutor().getNome());
	    }
		
		return dto;
	}
}
