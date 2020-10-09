package com.milkpointapi.api;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.milkpointapi.model.Laticinio;
import com.milkpointapi.model.Retirada;
import com.milkpointapi.model.Tanque;
import com.milkpointapi.service.LaticinioService;
import com.milkpointapi.service.RetiradaService;
import com.milkpointapi.service.TanqueService;

@RestController
@RequestMapping("/api")
public class RetiradaResource {

	@Autowired
	private RetiradaService service;

	@Autowired
	private TanqueService tanqueService;

	@Autowired
	private LaticinioService laticinioService;

	public ResponseEntity<Retirada> add(Retirada retirada) {

		if (retirada != null) {
			service.save(retirada);
			return ResponseEntity.ok(retirada);
		}

		return ResponseEntity.notFound().build();
	}

	public ZonedDateTime data() {
		ZonedDateTime data = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
		return data;
	}

	@PostMapping("/retirada")
	public ResponseEntity<Retirada> retirada(@RequestParam("quantidade") float quantidade,
			@RequestParam("idLat") Long idLat, @RequestParam("idTanque") Long idTanque) {
		Tanque tanque = tanqueService.findOne(idTanque);
		Laticinio laticinio = laticinioService.findOne(idLat);
		Retirada retirada = new Retirada();
		retirada.setDataNow(data());
		retirada.setDataSolicitacao(data());
		retirada.setLaticinio(laticinio);
		retirada.setTanque(tanque);
		retirada.setQuantidade(quantidade);
		retirada.setValor(quantidade * 0.84);
		tanqueService.save(tanque);
		return add(retirada);
	}

	@PostMapping("/retirada/confirmacao")
	public ResponseEntity<Retirada> confirmacao(@RequestParam("confirmacao") boolean confirmacao,
			@RequestParam("idRetirada") Long idRetirada, @RequestParam("efetuou") String nomeEfetuou,
			@RequestParam("observacao") String observacao) {

		Retirada retirada = service.findOne(idRetirada);

		if (retirada != null) {
			retirada.setConfirmacao(confirmacao);
			retirada.setDataNow(data());

			if (confirmacao) {
				Tanque tanque = retirada.getTanque();
				tanque.setQtdAtual(tanque.getQtdAtual() - retirada.getQuantidade());
				tanque.setQtdRestante(tanque.getQtdRestante() + retirada.getQuantidade());
			} else {
				retirada.setExcluido(true);
				retirada.setEfetuou(nomeEfetuou);
				if (observacao.isEmpty())
					retirada.setObservacao("Não informado!");
				else
					retirada.setObservacao(observacao);
			}
			service.save(retirada);
			return ResponseEntity.ok(retirada);
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/retirada/listatodos")
	public List<Retirada> listAll() {
		return service.findAll();
	}

	@GetMapping("/retirada/listapendentes")
	public List<Retirada> buscaPendentes() {
		return service.buscaPendentes();
	}

	@GetMapping("/retirada/resolvidos")
	public List<Retirada> buscaResolvidos() {
		return service.buscaResolvidos();
	}

}
