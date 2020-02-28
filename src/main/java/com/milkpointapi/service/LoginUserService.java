package com.milkpointapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milkpointapi.model.Laticinio;
import com.milkpointapi.model.Produtor;
import com.milkpointapi.model.Responsavel;

@Service
public class LoginUserService {

	@Autowired
	private ProdutorService prodService;

	@Autowired
	private LaticinioService latService;

	@Autowired
	private ResponsavelService responsavelService;

	public Object login(String email, String senha) {

		Produtor produtor = prodService.buscaLogin(email);
		Laticinio laticinio = latService.buscaLogin(email);
		Responsavel responsavel = responsavelService.buscaLogin(email);

		if (produtor != null) {

			return produtor;

		} else if (laticinio != null) {

			return laticinio;
		} else if (responsavel != null) {

			return responsavel;
		}

		return null;
	}
}
