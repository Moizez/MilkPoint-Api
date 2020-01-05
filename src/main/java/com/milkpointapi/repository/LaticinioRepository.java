package com.milkpointapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.milkpointapi.model.Laticinio;

@Repository
public interface LaticinioRepository extends JpaRepository<Laticinio, Long> {
	@Query
	public Laticinio findByEmailIgnoreCaseContaining(String email);
}
