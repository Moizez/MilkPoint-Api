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
		if (deposito.getTanque().getResponsavel().getSms()) {
			new SmsService().send(deposito);
		}
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
}