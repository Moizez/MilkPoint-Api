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
	
	public List<Retirada> buscaResolvidos() {
		return repository.buscaResolvidos();
	}
	
	public List<Retirada> buscaConfirmados(Long id) {
		return repository.buscaConfirmados(id);
	}
	
	public List<Retirada> buscaLaticinio(String nome) {
		return repository.buscaLaticinio(nome);
	}
	
	public List<Retirada> buscaCancelados(Long id) {
		return repository.buscaCancelados(id);
	}
	
	public List<Retirada> buscaTodosConfirmados() {
		return repository.buscaTodosConfirmados();
	}
	
	public List<Retirada> buscaTodosCancelados() {
		return repository.buscaTodosCancelados();
	}
	
	public List<Retirada> buscaPendentesPorLaticinio(Long id) {
		return repository.buscaPendentesPorLaticinio(id);
	}
	
	public List<Retirada> buscaRetiradasPendentesPorTanque(Long id) {
		return repository.buscaRetiradasPendentesPorTanque(id);
	}
	
	public List<Retirada> buscaRetiradasPorTanqueResponsavel(Long id) {
		return repository.buscaRetiradasPorTanqueResponsavel(id);
	}

	public List<Retirada> buscaRetiradasConfirmadasPorTanqueResponsavel(Long id) {
		return repository.buscaRetiradasConfirmadasPorTanqueResponsavel(id);
	}

	public List<Retirada> buscaRetiradasCanceladasPorTanqueResponsavel(Long id) {
		return repository.buscaRetiradasCanceladasPorTanqueResponsavel(id);
	}
}
