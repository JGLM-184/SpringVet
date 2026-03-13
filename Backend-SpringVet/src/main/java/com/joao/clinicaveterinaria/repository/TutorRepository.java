package com.joao.clinicaveterinaria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joao.clinicaveterinaria.model.entity.Tutor;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long>{

	Optional<Tutor> findByNome(String nome);
}
