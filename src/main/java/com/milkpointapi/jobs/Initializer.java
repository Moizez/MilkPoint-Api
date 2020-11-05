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
		createUserAdmin();
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
			tec.setCpf("305.669.060-90");
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

		/*if (tec2 == null) {
			tec2 = new Tecnico();
			tec2.setNome("Jeferson Queiroga");
			tec2.setApelido("Jeff");
			tec2.setCpf("737.896.540-51");
			tec2.setDataNascimento(new Date());
			tec2.setCep("63700445");
			tec2.setUf("CE");
			tec2.setLocalidade("Crateús");
			tec2.setBairro("Vaqueiros");
			tec2.setLogradouro("Rua das Amélias");
			tec2.setComplemento("Próximo a UFC");
			tec2.setEmail("jeff@gmail.com");
			tec2.setPassword("123");
			tec2.setDescricao("Técinco responsável pela região urbana de Crateús");

			tecnicoService.save(tec2);

		}*/
	}

	private void createProdutor() {

		Produtor pro = produtorService.buscaLogin("antonio@gmail.com");
		Produtor pro2 = produtorService.buscaLogin("vina@gmail.com");
		Produtor pro3 = produtorService.buscaLogin("julia@gmail.com");

		if (pro == null) {
			pro = new Produtor();
			pro.setNome("Antônio Almeida");
			pro.setApelido("Antônio");
			pro.setCpf("835.859.120-95");
			pro.setPhoneNumber("(87)91765-4582");
			pro.setDataNascimento(new Date());
			pro.setStatus(true);
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
		
		if (pro2 == null) {
			pro2 = new Produtor();
			pro2.setNome("Vinícius Carneiro");
			pro2.setApelido("Vina");
			pro2.setCpf("207.861.190-55");
			pro2.setPhoneNumber("(84)95059-3607");
			pro2.setDataNascimento(new Date());
			pro2.setStatus(true);
			pro2.setCep("59600-050");
			pro2.setUf("RN");
			pro2.setLocalidade("Mossoró");
			pro2.setBairro("Centro");
			pro2.setLogradouro("Avenida Dix-Sept Rosado");
			pro2.setComplemento("Próximo a Honda");
			pro2.setEmail("vina@gmail.com");
			pro2.setPassword("123");
			pro2.setDescricao("Sem descrição");

			produtorService.save(pro2);

		}
		
		if (pro3 == null) {
			pro3 = new Produtor();
			pro3.setNome("Júlia Queiroz");
			pro3.setApelido("Dona Júlia");
			pro3.setCpf("915.718.530-14");
			pro3.setPhoneNumber("(85)92493-5076");
			pro3.setDataNascimento(new Date());
			pro3.setStatus(true);
			pro3.setCep("60010-400");
			pro3.setUf("CE");
			pro3.setLocalidade("Fortaleza");
			pro3.setBairro("Jacarecanga");
			pro3.setLogradouro("Messias Filomeno");
			pro3.setComplemento("Próximo ao Banco do Brasil");
			pro3.setEmail("julia@gmail.com");
			pro3.setPassword("123");
			pro3.setDescricao("Sem descrição");

			produtorService.save(pro3);

		}
	}

	private void createResponsavel() {
		Responsavel resp = responsavelService.buscaLogin("leo@gmail.com");
		Responsavel resp2 = responsavelService.buscaLogin("ieda@gmail.com");
		Responsavel resp3 = responsavelService.buscaLogin("charles@gmail.com");
		Responsavel resp4 = responsavelService.buscaLogin("caio@gmail.com");

		if (resp == null) {
			resp = new Responsavel();
			resp.setNome("Leandro Rêgo");
			resp.setApelido("Leandro");
			resp.setCpf("590.321.140-26");
			resp.setPhoneNumber("(84)99257-9601");
			resp.setDataNascimento(new Date());
			resp.setCep("59900-000");
			resp.setUf("RN");
			resp.setStatus(true);
			resp.setLocalidade("Pau dos Ferros");
			resp.setBairro("Manoel Deodato");
			resp.setLogradouro("Rua da Libertação");
			resp.setComplemento("Próximo a quadra de esportes");
			resp.setEmail("leo@gmail.com");
			resp.setPassword("123");
			resp.setDescricao("Responsável em fase de testes");
			responsavelService.save(resp);
		}

		if (resp2 == null) {
			resp2 = new Responsavel();
			resp2.setNome("Iêda Carolina");
			resp2.setApelido("Iedinha");
			resp2.setCpf("431.871.760-70");
			resp2.setPhoneNumber("(84)90293-5267");
			resp2.setDataNascimento(new Date());
			resp2.setCep("59900-000");
			resp2.setUf("RN");
			resp2.setStatus(true);
			resp2.setLocalidade("Pau dos Ferros");
			resp2.setBairro("Centro");
			resp2.setLogradouro("13 de Maio");
			resp2.setComplemento("Próximo ao mercantil São Luiz");
			resp2.setEmail("ieda@gmail.com");
			resp2.setPassword("123");
			resp2.setDescricao("Responsável aprovado");
			responsavelService.save(resp2);
		}
		
		if (resp3 == null) {
			resp3 = new Responsavel();
			resp3.setNome("Charles Lopes");
			resp3.setApelido("Aristoteles");
			resp3.setCpf("451.758.290-68");
			resp3.setPhoneNumber("(84)92163-5516");
			resp3.setDataNascimento(new Date());
			resp3.setCep("59908-000");
			resp3.setUf("RN");
			resp3.setStatus(true);
			resp3.setLocalidade("São Francisco do Oeste");
			resp3.setBairro("Centro");
			resp3.setLogradouro("São Geraldo II");
			resp3.setComplemento("Próximo ao bar do Geraldo");
			resp3.setEmail("charles@gmail.com");
			resp3.setPassword("123");
			resp3.setDescricao("Responsável aprovado");
			responsavelService.save(resp3);
		}
		
		if (resp4 == null) {
			resp4 = new Responsavel();
			resp4.setNome("Caio Anderson");
			resp4.setApelido("Caio");
			resp4.setCpf("653.542.420-02");
			resp4.setPhoneNumber("(84)92811-0111");
			resp4.setDataNascimento(new Date());
			resp4.setCep("59995-000");
			resp4.setUf("RN");
			resp4.setStatus(true);
			resp4.setLocalidade("Água Nova");
			resp4.setBairro("Centro");
			resp4.setLogradouro("José Netuno");
			resp4.setComplemento("Próximo ao mercantil do Zé");
			resp4.setEmail("caio@gmail.com");
			resp4.setPassword("123");
			resp4.setDescricao("Responsável aprovado");
			responsavelService.save(resp4);
		}

	}

	private void createLaticinio() {
		Laticinio lat = laticinioService.buscaLogin("leiteecia@gmail.com");
		Laticinio lat2 = laticinioService.buscaLogin("leitedaterra@gmail.com");
		Laticinio lat3 = laticinioService.buscaLogin("nutrileite@gmail.com");

		if (lat == null) {
			lat = new Laticinio();
			lat.setNome("Francisco Ribeiro");
			lat.setNomeFantasia("Leite & Cia");
			lat.setCnpj("10.447.422/0001-63");
			lat.setPhoneNumber("(84)96101-3384");
			lat.setDataCriacao(new Date());
			lat.setEmail("leiteecia@gmail.com");
			lat.setPassword("123");
			lat.setCep("59900-000");
			lat.setStatus(true);
			lat.setUf("RN");
			lat.setLocalidade("Pau dos Ferros");
			lat.setBairro("Centro");
			lat.setLogradouro("13 de Maio");
			lat.setComplemento("Próximo a capela Santa Tereza");
			lat.setDescricao("Laticínio aguardando alvará da prefeitura");
			laticinioService.save(lat);
		}
		
		if (lat2 == null) {
			lat2 = new Laticinio();
			lat2.setNome("Luciano Max");
			lat2.setNomeFantasia("Leite da Terra");
			lat2.setCnpj("04.464.269/0001-80");
			lat2.setPhoneNumber("(88)95707-6975");
			lat2.setDataCriacao(new Date());
			lat2.setEmail("leitedaterra@gmail.com");
			lat2.setPassword("123");
			lat2.setCep("62980-000");
			lat2.setStatus(true);
			lat2.setUf("CE");
			lat2.setLocalidade("Iracema");
			lat2.setBairro("Centro");
			lat2.setLogradouro("Alto do Açude");
			lat2.setComplemento("Próximo a Igreja Santa Maria");
			lat2.setDescricao("Laticínio regularizado");
			laticinioService.save(lat2);
		}
		
		if (lat3 == null) {
			lat3 = new Laticinio();
			lat3.setNome("Carlos Lima");
			lat3.setNomeFantasia("NutriLeite");
			lat3.setCnpj("68.618.880/0001-06");
			lat3.setPhoneNumber("(88)95571-2807");
			lat3.setDataCriacao(new Date());
			lat3.setEmail("nutrileite@gmail.com");
			lat3.setPassword("123");
			lat3.setCep("63460-000");
			lat3.setStatus(true);
			lat3.setUf("CE");
			lat3.setLocalidade("Pereiro");
			lat3.setBairro("Centro");
			lat3.setLogradouro("Arco da Aliança");
			lat3.setComplemento("Próximo a Igreja de St. Antônio");
			lat3.setDescricao("Laticínio regularizado");
			laticinioService.save(lat3);
		}
		
		
	}

	private void createTanque() {

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
			tan.setComplemento("Próximo a farmácia Padre Carlos");
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
			tan2.setComplemento("Próximo ao sorvete KiDelicia");
			tan2.setComunidade("Riacho Doce");
			tan2.setLatitude(-5.192423);
			tan2.setLongitude(-40.676846);

			tanqueService.save(tan2);

		}
	}

}
