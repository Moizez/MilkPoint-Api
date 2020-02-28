package com.milkpointapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milkpointapi.model.Produtor;
import com.milkpointapi.repository.ProdutorRepository;

@Service
public class ProdutorService {

	@Autowired
	private ProdutorRepository repository;

	public Produtor save(Produtor produtor) {
		return repository.saveAndFlush(produtor);
	}

	public List<Produtor> findAll() {
		return repository.findAll();
	}

	public Produtor findOne(Long id) {
		return repository.getOne(id);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public Produtor buscaLogin(String email) {
		return repository.findByEmailIgnoreCaseContaining(email);
	}
	
	public List<Produtor> searchFor(String nome, String apelido, String cpf, String email) {
		return repository.findByNomeOrApelidoOrCpfOrEmailLike(nome, apelido, cpf, email);
	}

}
