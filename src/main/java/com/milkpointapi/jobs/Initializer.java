	package com.milkpointapi.jobs;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.milkpointapi.enums.Capacidade;
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
		//createUserAdmin();
		createTecnico();
		createResponsavel();
		createProdutor();
		createLaticinio();
		//createTanque();

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
		Tecnico tec2 = tecnicoService.buscaLogin("jeff@gmail.com");

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

		if (tec2 == null) {
			tec2 = new Tecnico();
			tec2.setNome("Jeferson Queiroga");
			tec2.setApelido("Jeff.");
			tec2.setCpf("09643587566");
			tec2.setDataNascimento(new Date());
			tec2.setCep("63700-000");
			tec2.setUf("CE");
			tec2.setLocalidade("Crateús");
			tec2.setBairro("Vaqueiros");
			tec2.setLogradouro("Rua das Amélias");
			tec2.setComplemento("Próximo a UFC");
			tec2.setEmail("jeff@gmail.com");
			tec2.setPassword("123");
			tec2.setDescricao("Técinco responsável pela região urbana de Crateús");

			tecnicoService.save(tec2);

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
		Responsavel resp2 = responsavelService.buscaLogin("ieda@gmail.com");

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

		if (resp2 == null) {
			resp2 = new Responsavel();
			resp2.setNome("Iêda Carolina");
			resp2.setApelido("Iedinha");
			resp2.setCpf("23424565665");
			resp2.setPhoneNumber("+5584981690743");
			resp2.setDataNascimento(new Date());
			resp2.setCep("59900-000");
			resp2.setUf("RN");
			resp2.setLocalidade("Pau dos Ferros");
			resp2.setBairro("Centro");
			resp2.setLogradouro("13 de Maio");
			resp2.setComplemento("Próximo ao mercantil São Luiz");
			resp2.setEmail("ieda@gmail.com");
			resp2.setPassword("123");
			resp2.setDescricao("Responsável aprovado");

			responsavelService.save(resp2);

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

	/*private void createTanque() {

		Tanque tan = tanqueService.findByNome("T-000");
		Tanque tan2 = tanqueService.findByNome("T-001");
		Responsavel resp = responsavelService.buscaLogin("leo@gmail.com");
		Responsavel resp2 = responsavelService.buscaLogin("ieda@gmail.com");
		Tecnico tec = tecnicoService.buscaLogin("jr@gmail.com");
		Tecnico tec2 = tecnicoService.buscaLogin("jeff@gmail.com");

		if (tan == null) {
			tan = new Tanque();
			tan.setNome("T-000");
			tan.setResponsavel(resp);
			tan.setTecnico(tec);
			tan.setCapacidade(Capacidade.QUATROMILEQUINHENTOS);
			tan.setStatus(true);
			tan.setQtdAtual((float) 2500);
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

		if (tan2 == null) {
			tan2 = new Tanque();
			tan2.setNome("T-001");
			tan2.setResponsavel(resp2);
			tan2.setTecnico(tec2);
			tan2.setCapacidade(Capacidade.TRESMIL);
			tan2.setStatus(true);
			tan2.setQtdAtual((float) 1500);
			tan2.setQtdRestante(3000 - tan2.getQtdAtual());
			tan2.setDataCriacao(new Date());
			tan2.setTipo(Tipo.CAPRINO);
			tan2.setCep("63700-000");
			tan2.setUf("CE");
			tan2.setLocalidade("Crateús");
			tan2.setBairro("Riacho do Meio");
			tan2.setLogradouro("Joel Praxedes");
			tan2.setComplemento("Próximo a capela de Santo Antônio");
			tan2.setComunidade("Riacho Doce");
			tan2.setLatitude(-5.192423);
			tan2.setLongitude(-40.676846);

			tanqueService.save(tan2);

		}
	}*/

}
