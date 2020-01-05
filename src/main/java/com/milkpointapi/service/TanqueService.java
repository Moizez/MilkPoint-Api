package com.milkpointapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milkpointapi.model.Tanque;
import com.milkpointapi.repository.TanqueRepository;

@Service
public class TanqueService {

	@Autowired
	private TanqueRepository repository;

	public Tanque save(Tanque tanque) {
		return repository.saveAndFlush(tanque);
	}

	public List<Tanque> findAll() {
		return repository.findAll();
	}

	public Tanque findOne(Long id) {
		return repository.getOne(id);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

}
