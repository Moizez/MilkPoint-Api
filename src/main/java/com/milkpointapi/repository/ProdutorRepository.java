package com.milkpointapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.milkpointapi.model.Produtor;

@Repository
public interface ProdutorRepository extends JpaRepository<Produtor, Long> {

	@Query
	public Produtor findByEmailIgnoreCaseContaining(String email);
}
