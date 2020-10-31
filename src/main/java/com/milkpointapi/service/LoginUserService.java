package com.milkpointapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milkpointapi.model.Laticinio;
import com.milkpointapi.model.Produtor;
import com.milkpointapi.model.Responsavel;
import com.milkpointapi.model.Tecnico;

@Service
public class LoginUserService {

	@Autowired
	private ProdutorService produtorService;

	@Autowired
	private LaticinioService laticinioService;

	@Autowired
	private ResponsavelService responsavelService;

	@Autowired
	private TecnicoService tecnicoService;

	public Object login(String email, String senha) {

		Produtor produtor = produtorService.findByEmailAndPassword(email, senha);
		Laticinio laticinio = laticinioService.findByEmailAndPassword(email, senha);
		Responsavel responsavel = responsavelService.findByEmailAndPassword(email, senha);
		Tecnico tecnico = tecnicoService.findByEmailAndPassword(email, senha);

		if (produtor != null) {
			return produtor;

		} else if (laticinio != null) {
			return laticinio;

		} else if (responsavel != null) {
			return responsavel;

		} else if (tecnico != null) {
			return tecnico;
		}

		return null;
	}
}
