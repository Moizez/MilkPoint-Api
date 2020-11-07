package com.milkpointapi.api;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;

import com.milkpointapi.enums.Capacidade;
import com.milkpointapi.model.Tanque;
import com.milkpointapi.service.TanqueService;

@RestController
@RequestMapping("/api")
public class TanqueResource {

	@Autowired
	private TanqueService tanqueService;
	
	public ZonedDateTime data() {
		ZonedDateTime data = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
		return data;
	}

	@SuppressWarnings("unused")
	@PostMapping("/tanque")
	public ResponseEntity<Tanque> add(@RequestBody Tanque tanque) {
		Tanque tanqueAtual = tanqueService.findByNome(tanque.getNome());

		Random aleatorio = new Random();
		int valor = aleatorio.nextInt(1000);

		if (tanque != null) {
			if (tanque.getCapacidade() == Capacidade.MIL) {
				tanque.setQtdRestante(1000 - tanque.getQtdAtual());
			} else if (tanque.getCapacidade() == Capacidade.DOISMIL) {
				tanque.setQtdRestante(2000 - tanque.getQtdAtual());
			} else if (tanque.getCapacidade() == Capacidade.TRESMIL) {
				tanque.setQtdRestante(3000 - tanque.getQtdAtual());
			} else if (tanque.getCapacidade() == Capacidade.QUATROMIL) {
				tanque.setQtdRestante(4000 - tanque.getQtdAtual());
			} else if (tanque.getCapacidade() == Capacidade.QUATROMILEQUINHENTOS) {
				tanque.setQtdRestante(4500 - tanque.getQtdAtual());
			}

			if (tanque.getNome().equals(tanqueAtual.getNome()))
				tanque.setNome("T-" + valor + "A");

			tanqueService.save(tanque);
			return new ResponseEntity<Tanque>(tanque, HttpStatus.CREATED);
		}
		return new ResponseEntity<Tanque>(tanque, HttpStatus.CONFLICT);

	}

	@GetMapping("/tanque")
	public List<Tanque> listar() {
		return tanqueService.findAll();
	}

	@GetMapping("/tanque/{id}")
	public ResponseEntity<Tanque> buscar(@PathVariable Long id) {
		Tanque tanque = tanqueService.findOne(id);

		if (tanque == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(tanque);
	}

	@PutMapping("/tanque/{id}")
	public ResponseEntity<Tanque> update(@PathVariable Long id, @RequestBody Tanque tanque) {
		Tanque tanqueAtual = tanqueService.findOne(id);

		if (tanqueAtual == null)
			return ResponseEntity.notFound().build();

		// CARACTERISTICAS
		if (tanque.getNome() == null || tanque.getNome() == "")
			tanque.setNome(tanqueAtual.getNome());

		if (tanque.getTipo() == null)
			tanque.setTipo(tanqueAtual.getTipo());

		if (tanque.getResponsavel() == null)
			tanque.setResponsavel(tanqueAtual.getResponsavel());

		if (tanque.getTecnico() == null)
			tanque.setTecnico(tanqueAtual.getTecnico());

		if (tanque.getQtdAtual() == 0)
			tanque.setQtdAtual(tanqueAtual.getQtdAtual());

		if (tanque.getQtdRestante() == 0)
			tanque.setQtdRestante(tanqueAtual.getQtdRestante());

		if (tanque.getCapacidade() == null)
			tanque.setCapacidade(tanqueAtual.getCapacidade());

		if (tanque.getCapacidade() == Capacidade.MIL) {
			tanque.setQtdRestante(1000 - tanque.getQtdAtual());
		} else if (tanque.getCapacidade() == Capacidade.DOISMIL) {
			tanque.setQtdRestante(2000 - tanque.getQtdAtual());
		} else if (tanque.getCapacidade() == Capacidade.TRESMIL) {
			tanque.setQtdRestante(3000 - tanque.getQtdAtual());
		} else if (tanque.getCapacidade() == Capacidade.QUATROMIL) {
			tanque.setQtdRestante(4000 - tanque.getQtdAtual());
		} else if (tanque.getCapacidade() == Capacidade.QUATROMILEQUINHENTOS) {
			tanque.setQtdRestante(4500 - tanque.getQtdAtual());
		}

		// ENDEREÇO
		if (tanque.getCep() == null)
			tanque.setCep(tanqueAtual.getCep());

		if (tanque.getUf() == null)
			tanque.setUf(tanqueAtual.getUf());

		if (tanque.getLocalidade() == null)
			tanque.setLocalidade(tanqueAtual.getLocalidade());

		if (tanque.getBairro() == null)
			tanque.setBairro(tanqueAtual.getBairro());

		if (tanque.getLogradouro() == null)
			tanque.setLogradouro(tanqueAtual.getLogradouro());

		if (tanque.getComunidade() == null)
			tanque.setComunidade(tanqueAtual.getComunidade());

		if (tanque.getComplemento() == null)
			tanque.setComplemento(tanqueAtual.getComplemento());

		if (tanque.getLatitude() == 0 || tanque.getLongitude() == 0) {
			tanque.setLatitude(tanqueAtual.getLatitude());
			tanque.setLongitude(tanqueAtual.getLongitude());
		}

		if (!tanque.isStatus()) {
			tanque.setDataInativado(data());
			if (tanque.getObservacao() == null || tanque.getObservacao().equals("")) {
				if (tanqueAtual.getObservacao() == null || tanqueAtual.getObservacao().equals("")) {
					tanque.setObservacao("Motivo não informado");
				} else {
					tanque.setObservacao(tanqueAtual.getObservacao());
				}
			}
		}
		
		BeanUtils.copyProperties(tanque, tanqueAtual, "id", "capacidade", "dataCriacao");
		tanqueAtual = tanqueService.save(tanqueAtual);
		return ResponseEntity.ok(tanqueAtual);
	}

	@DeleteMapping("/tanque/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Tanque Tanque = tanqueService.findOne(id);
		if (Tanque == null) {
			return ResponseEntity.notFound().build();
		}
		tanqueService.delete(id);
		return ResponseEntity.noContent().build();

	}

	@PutMapping("/tanque/location/{id}/{latitude}/{longitude}")
	public ResponseEntity<Tanque> location(@PathVariable Long id, @PathVariable("latitude") double latitude,
			@PathVariable("longitude") double longitude) {
		Tanque tanque = tanqueService.findOne(id);
		if (tanque == null) {
			return ResponseEntity.notFound().build();
		}
		tanque.setLatitude(latitude);
		tanque.setLongitude(longitude);
		tanqueService.save(tanque);
		return ResponseEntity.ok(tanque);
	}

	@GetMapping("/tanque/ativos")
	public List<Tanque> buscaTanqueAtivos() {
		return tanqueService.buscaTanqueAtivos();
	}

	@GetMapping("/tanque/inativos")
	public List<Tanque> buscaTanqueInativos() {
		return tanqueService.buscaTanqueInativos();
	}

	@GetMapping("/tanque/ativos/{idTecnico}")
	public List<Tanque> buscaTanquesAtivosPorTecnico(@PathVariable("idTecnico") Long id) {
		return tanqueService.buscaTanquesAtivosPorTecnico(id);
	}

	@GetMapping("/tanque/inativos/{idTecnico}")
	public List<Tanque> buscaTanquesInativosPorTecnico(@PathVariable("idTecnico") Long id) {
		return tanqueService.buscaTanquesInativosPorTecnico(id);
	}

}
