package com.milkpointapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.milkpointapi.model.Produtor;

@Repository
public interface ProdutorRepository extends JpaRepository<Produtor, Long> {

	@Query
	public Produtor findByEmailIgnoreCaseContaining(String email);
	
	@Query
	public List<Produtor> findByNomeIgnoreCaseContaining(String nome);
}
