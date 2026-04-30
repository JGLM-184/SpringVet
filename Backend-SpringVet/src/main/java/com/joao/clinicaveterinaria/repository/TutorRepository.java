package com.joao.clinicaveterinaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joao.clinicaveterinaria.model.Tutor;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long>{

	List<Tutor> findByNomeContainingIgnoreCase(String nome);

}
