package com.milkpointapi.jobs;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.milkpointapi.enums.Capacidade;
import com.milkpointapi.enums.Status;
import com.milkpointapi.enums.Tipo;
import com.milkpointapi.model.Laticinio;
import com.milkpointapi.model.Produtor;
import com.milkpointapi.model.Responsavel;
import com.milkpointapi.model.Tanque;
import com.milkpointapi.model.Tecnico;
import com.milkpointapi.model.UserInfo;
import com.milkpointapi.service.LaticinioService;
import com.milkpointapi.service.ProdutorService;
import com.milkpointapi.service.ResponsavelService;
import com.milkpointapi.service.TanqueService;
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

	@Autowired
	private TanqueService tanqueService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		createUserAdmin();
		createTecnico();
		createResponsavel();
		createProdutor();
		createLaticinio();
		createTanque();

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
			tec.setDataNascimento(new Date());
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
			pro.setPhoneNumber("+5584981690739");
			pro.setDataNascimento(new Date());
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
			resp.setPhoneNumber("+5584981690739");
			resp.setDataNascimento(new Date());
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
			lat.setPhoneNumber("+5584981690739");
			lat.setDataCriacao(new Date());
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

	private void createTanque() {

		Tanque tan = tanqueService.findByNome("T-000");
		Responsavel resp = responsavelService.buscaLogin("leo@gmail.com");
		Tecnico tec = tecnicoService.buscaLogin("jr@gmail.com");

		if (tan == null) {
			tan = new Tanque();
			tan.setNome("T-000");
			tan.setResponsavel(resp);
			tan.setTecnico(tec);
			tan.setCapacidade(Capacidade.QUATROMILEQUINHENTOS);
			tan.setStatus(Status.ATIVO);
			tan.setQtdRestante(4500 - tan.getQtdAtual());
			tan.setDataCriacao(new Date());
			tan.setTipo(Tipo.BOVINO);
			tan.setCep("59900-000");
			tan.setUf("RN");
			tan.setLocalidade("Pau dos Ferros");
			tan.setBairro("Riacho do Meio");
			tan.setLogradouro("Joel Praxedes");
			tan.setComplemento("Próximo a capela de Santo Antônio");
			tan.setComunidade("Riacho Doce");
			tan.setLatitude(-6.113661);
			tan.setLongitude(-38.223907);

			tanqueService.save(tan);

		}
	}

}
