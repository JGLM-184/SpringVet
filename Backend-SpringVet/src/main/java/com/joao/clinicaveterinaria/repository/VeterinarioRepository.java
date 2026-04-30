package com.joao.clinicaveterinaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joao.clinicaveterinaria.model.Veterinario;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long>{
	
	List<Veterinario> findByNomeContainingIgnoreCase(String nome);
	long countByAtivoTrue();
}
