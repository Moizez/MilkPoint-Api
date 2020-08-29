package com.milkpointapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.milkpointapi.model.Retirada;
import com.milkpointapi.repository.RetiradaRepository;

@Service
public class RetiradaService {

	@Autowired
	private RetiradaRepository repository;

	public void save(Retirada retirada) {
		repository.save(retirada);
		new SmsService().send(retirada);
	}

	public List<Retirada> findAll() {
		return repository.findAll(Sort.by(Sort.Direction.DESC, "dataNow"));
	}

	public Retirada findOne(Long id) {
		return repository.getOne(id);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public List<Retirada> buscaPendentes() {
		return repository.buscaPendentes();
	}
}
