package com.milkpointapi.api;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.milkpointapi.model.Deposito;
import com.milkpointapi.model.Produtor;
import com.milkpointapi.model.Tanque;
import com.milkpointapi.service.DepositoService;
import com.milkpointapi.service.ProdutorService;
import com.milkpointapi.service.TanqueService;

@RestController
@RequestMapping("/api")
public class DepositoResource {

	@Autowired
	private DepositoService service;

	@Autowired
	private TanqueService tanqueService;

	@Autowired
	private ProdutorService produtorService;

	public ResponseEntity<Deposito> add(Deposito deposito) {

		if (deposito != null) {
			service.save(deposito);
			return ResponseEntity.ok(deposito);
		}

		return ResponseEntity.notFound().build();
	}

	public String data() {
		DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		String dataFormatada = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).format(formatterData);
		return dataFormatada;
	}

	public String hora() {
		DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
		String horaFormatada = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).format(formatterHora);
		return horaFormatada;
	}

	@PostMapping("/deposito")
	public ResponseEntity<Deposito> deposito(@RequestParam("quantidade") float quantidade,
			@RequestParam("idProd") Long idProd, @RequestParam("idTanque") Long idTanque) {
		Tanque tanque = tanqueService.findOne(idTanque);
		Produtor produtor = produtorService.findOne(idProd);
		Deposito deposito = new Deposito();
		deposito.setDataNow(data());
		deposito.setHoraNow(hora());
		deposito.setProdutor(produtor);
		deposito.setTanque(tanque);
		deposito.setQuantidade(quantidade);
		tanqueService.save(tanque);
		return add(deposito);
	}

	@PostMapping("/deposito/confirmacao")
	public ResponseEntity<Deposito> confirmacao(@RequestParam("confirmacao") boolean confirmacao,
			@RequestParam("idDeposito") Long idDeposito, @RequestParam("efetuou") String nomeEfetuou) {

		Deposito deposito = service.findOne(idDeposito);

		if (deposito != null) {
			deposito.setConfirmacao(confirmacao);
			deposito.setDataNow(data());
			deposito.setHoraNow(hora());

			if (confirmacao) {
				Tanque tanque = deposito.getTanque();
				tanque.setQtdAtual(tanque.getQtdAtual() + deposito.getQuantidade());
				tanque.setQtdRestante(tanque.getQtdRestante() - deposito.getQuantidade());
			} else {
				deposito.setExcluido(true);
				deposito.setEfetuou(nomeEfetuou);
			}
			service.save(deposito);
			return ResponseEntity.ok(deposito);
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/deposito/listatodos")
	public List<Deposito> listaTodos() {
		return service.findAll();
	}

	@GetMapping("/deposito/listapendentes")
	public List<Deposito> listaPendentes() {
		return service.buscaPendentes();
	}
}
