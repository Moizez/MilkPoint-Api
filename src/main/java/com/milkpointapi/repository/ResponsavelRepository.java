package com.milkpointapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.milkpointapi.model.Responsavel;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {

	@Query
	public Responsavel findByEmailIgnoreCaseContaining(String email);

	@Query
	public List<Responsavel> findByNomeIgnoreCaseContaining(String nome);

	@Query
	public List<Responsavel> findByApelidoIgnoreCaseContaining(String apelido);

}
