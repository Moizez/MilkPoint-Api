package com.milkpointapi.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.milkpointapi.model.Laticinio;
import com.milkpointapi.model.Produtor;
import com.milkpointapi.model.Responsavel;
import com.milkpointapi.model.Tecnico;
import com.milkpointapi.model.UserInfo;
import com.milkpointapi.service.LaticinioService;
import com.milkpointapi.service.ProdutorService;
import com.milkpointapi.service.ResponsavelService;
import com.milkpointapi.service.TecnicoService;
import com.milkpointapi.service.UserService;

@Component
public class Initializer implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserService userService;

	@Autowired
	private TecnicoService tecnicoService;

	@Autowired
	private ResponsavelService responsavelService;

	@Autowired
	private ProdutorService produtorService;

	@Autowired
	private LaticinioService laticinioService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("----- Criando Usuários Padrões ------");
		createUserAdmin();
		createTecnico();
		createResponsavel();
		createProdutor();
		createLaticinio();

		System.out.println("----- Usuários Criados com Sucesso! -----");

	}

	private void createUserAdmin() {
		UserInfo usuario = userService.findByRoleAdmin();

		if (usuario == null) {
			usuario = new UserInfo();
			usuario.setFirstName("Moisés");
			usuario.setLastName("Henrique");
			usuario.setEmail("moizez@gmail.com");
			usuario.setPassword("123");
			usuario.setRole("ADMIN");
			userService.save(usuario);

		}

	}

	private void createTecnico() {

		Tecnico tec = tecnicoService.buscaLogin("jr@gmail.com");

		if (tec == null) {
			tec = new Tecnico();
			tec.setNome("Ivan Junior");
			tec.setApelido("Ivan Jr.");
			tec.setCpf("09643587686");
			tec.setDataNascimento(null);
			tec.setCep("63700-000");
			tec.setUf("CE");
			tec.setLocalidade("Crateús");
			tec.setBairro("Venâncios");
			tec.setLogradouro("Rodovia BR-226, Km 03, s/n");
			tec.setComplemento("Próximo a UFCE");
			tec.setEmail("jr@gmail.com");
			tec.setPassword("123");
			tec.setDescricao("Técinco responsável pela região rural de Crateús");

			tecnicoService.save(tec);

		}
	}

	private void createProdutor() {

		Produtor pro = produtorService.buscaLogin("antonio@gmail.com");

		if (pro == null) {
			pro = new Produtor();
			pro.setNome("Antônio Almeida");
			pro.setApelido("Antônio");
			pro.setCpf("43619901074");
			pro.setDataNascimento(null);
			pro.setCep("59905-000");
			pro.setUf("RN");
			pro.setLocalidade("Encanto");
			pro.setBairro("Encanto de Cima");
			pro.setLogradouro("Maria José");
			pro.setComplemento("Próximo aos correios");
			pro.setEmail("antonio@gmail.com");
			pro.setPassword("123");
			pro.setDescricao("Produtor no aguardo das documentações");

			produtorService.save(pro);

		}
	}

	private void createResponsavel() {
		Responsavel resp = responsavelService.buscaLogin("leo@gmail.com");

		if (resp == null) {
			resp = new Responsavel();
			resp.setNome("Leandro Rêgo");
			resp.setApelido("Leandro");
			resp.setCpf("98736378003");
			resp.setDataNascimento(null);
			resp.setCep("59900-000");
			resp.setUf("RN");
			resp.setLocalidade("Pau dos Ferros");
			resp.setBairro("Manoel Deodato");
			resp.setLogradouro("Bala Trocada");
			resp.setComplemento("Próximo ao bar do rasga bucho");
			resp.setEmail("leo@gmail.com");
			resp.setPassword("123");
			resp.setDescricao("Responsável em fase de testes");

			responsavelService.save(resp);

		}

	}

	private void createLaticinio() {
		Laticinio lat = laticinioService.buscaLogin("leiteecia@gmail.com");

		if (lat == null) {
			lat = new Laticinio();
			lat.setNome("Francisco Ribeiro");
			lat.setNomeFantasia("Leite & Cia");
			lat.setCnpj("66606211000180");
			lat.setDataCriacao(null);
			lat.setEmail("leiteecia@gmail.com");
			lat.setPassword("123");
			lat.setCep("59900-000");
			lat.setUf("RN");
			lat.setLocalidade("Pau dos Ferros");
			lat.setBairro("Centro");
			lat.setLogradouro("13 de Maio");
			lat.setComplemento("Próximo a capela");
			lat.setDescricao("Laticínio aguardando alvará da prefeitura");

			laticinioService.save(lat);

		}
	}

}
