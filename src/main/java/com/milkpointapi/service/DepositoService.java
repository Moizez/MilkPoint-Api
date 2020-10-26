package com.milkpointapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.milkpointapi.model.Deposito;
import com.milkpointapi.repository.DepositoRepository;

@Service
public class DepositoService {
	@Autowired
	private DepositoRepository repository;

	public void save(Deposito deposito) {
		repository.save(deposito);
	}

	public List<Deposito> findAll() {
		return repository.findAll(Sort.by(Sort.Direction.DESC, "dataNow"));
	}

	public Deposito findOne(Long id) {
		return repository.getOne(id);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public List<Deposito> buscaPendentes() {
		return repository.buscaPendentes();
	}
	
	public List<Deposito> buscaResolvidos() {
		return repository.buscaResolvidos();
	}
	
	public List<Deposito> buscaConfirmados(Long id) {
		return repository.buscaConfirmados(id);
	}
	
	public List<Deposito> buscaProdutor(String produtor) {
		return repository.buscaProdutor(produtor);
	}
	
	public List<Deposito> buscaPendentesPorProdutor(Long id){
		return repository.buscaPendentesPorProdutor(id);
	}
	
	public List<Deposito> buscaCancelados(Long id) {
		return repository.buscaCancelados(id);
	}
	
	public List<Deposito> buscaTodosConfirmados() {
		return repository.buscaTodosConfirmados();
	}
	
	public List<Deposito> buscaTodosCancelados() {
		return repository.buscaTodosCancelados();
	}

}