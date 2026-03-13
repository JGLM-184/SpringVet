package com.joao.clinicaveterinaria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joao.clinicaveterinaria.model.entity.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
		
	public Optional<Animal> findByNome(String nome);
}
