package com.milkpointapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.milkpointapi.model.Tanque;

@Repository
public interface TanqueRepository extends JpaRepository<Tanque, Long> {
	
}
