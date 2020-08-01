package com.milkpointapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milkpointapi.model.Tecnico;
import com.milkpointapi.repository.TecnicoRepository;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;

	public Tecnico save(Tecnico tecnico) {
		return repository.saveAndFlush(tecnico);
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	public Tecnico findOne(Long id) {
		return repository.getOne(id);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Tecnico findByEmailAndPassword(String email, String senha) {
		return repository.findByEmailAndPassword(email, senha);
	}

	public Tecnico buscaLogin(String email) {
		return repository.findByEmailIgnoreCaseContaining(email);
	}
	
	public List<Tecnico> searchFor(String nome, String apelido, String cpf, String email) {
		return repository.findByNomeOrApelidoOrCpfOrEmailLike(nome, apelido, cpf, email);
	}

}
