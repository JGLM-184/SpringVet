package com.joao.clinicaveterinaria.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joao.clinicaveterinaria.model.entity.Animal;
import com.joao.clinicaveterinaria.model.entity.Consulta;
import com.joao.clinicaveterinaria.model.entity.Veterinario;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long>{

	List<Consulta> findByVeterinario(Veterinario veterinario);
	List<Consulta> findByAnimal(Animal animal);
		
	List<Consulta> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);
	int countByStatus(String status);
	
	boolean existsByVeterinarioAndDataHora(Veterinario veterinario, LocalDateTime dataHora);
}