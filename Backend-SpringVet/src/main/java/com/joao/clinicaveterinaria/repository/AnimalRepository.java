package com.joao.clinicaveterinaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joao.clinicaveterinaria.model.entity.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
		
	List<Animal> findByTutorIdAndNomeContainingIgnoreCase(Long tutorId, String nome);
	public List<Animal> findByTutorId(Long id);
}
