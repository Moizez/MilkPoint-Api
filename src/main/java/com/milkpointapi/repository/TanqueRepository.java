package com.milkpointapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.milkpointapi.model.Tanque;

@Repository
public interface TanqueRepository extends JpaRepository<Tanque, Long> {
	
	@Query
	public List<Tanque> findByNomeIgnoreCaseContaining(String nome);
	
}
