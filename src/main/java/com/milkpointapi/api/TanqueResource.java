package com.milkpointapi.api;

import java.util.List;
import javax.validation.Valid;

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

import com.milkpointapi.enums.Capacidade;
import com.milkpointapi.enums.Status;
import com.milkpointapi.model.Tanque;
import com.milkpointapi.service.TanqueService;

@RestController
@RequestMapping("/api")
public class TanqueResource {

	@Autowired
	private TanqueService tanqueService;

	@PostMapping("/tanque")
	public ResponseEntity<Tanque> add(@RequestBody @Valid Tanque tanque) {
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

			if (tanque.getStatus() == null)
				tanque.setStatus(Status.ATIVO);

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
	public ResponseEntity<Tanque> update(@PathVariable Long id, @Valid @RequestBody Tanque tanque) {
		Tanque tanqueAtual = tanqueService.findOne(id);

		System.out.println("\nTANQUE DO APP........................");
		System.out.println("Nome: " + tanque.getNome());
		System.out.println("Tipo: " + tanque.getTipo());
		System.out.println("Capacidade: " + tanque.getCapacidade());
		System.out.println("Qtd. Atual: " + tanque.getQtdAtual());
		System.out.println("Qtd. Restante: " + tanque.getQtdRestante());
		System.out.println("Latitude: " + tanque.getLatitude());
		System.out.println("Longitude: " + tanque.getLongitude());
		System.out.println("Status: " + tanque.getStatus() + "\n");

		if (tanqueAtual == null)
			return ResponseEntity.notFound().build();

		// CARACTERISTICAS
		if (tanque.getNome() == "")
			tanque.setNome(tanqueAtual.getNome());

		if (tanque.getTipo() == null)
			tanque.setTipo(tanqueAtual.getTipo());

		if (tanque.getStatus() == null)
			tanque.setStatus(tanqueAtual.getStatus());

		if (tanque.getResponsavel() == null)
			tanque.setResponsavel(tanqueAtual.getResponsavel());

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
		if (tanque.getCep() == "")
			tanque.setCep(tanqueAtual.getCep());

		if (tanque.getUf() == "")
			tanque.setUf(tanqueAtual.getUf());

		if (tanque.getLocalidade() == "")
			tanque.setLocalidade(tanqueAtual.getLocalidade());

		if (tanque.getBairro() == "")
			tanque.setBairro(tanqueAtual.getBairro());

		if (tanque.getLogradouro() == "")
			tanque.setLogradouro(tanqueAtual.getLogradouro());

		if (tanque.getComunidade() == "")
			tanque.setComunidade(tanqueAtual.getComunidade());

		if (tanque.getComplemento() == "")
			tanque.setComplemento(tanqueAtual.getComplemento());

		if (tanque.getLatitude() == 0 || tanque.getLongitude() == 0) {
			tanque.setLatitude(tanqueAtual.getLatitude());
			tanque.setLongitude(tanqueAtual.getLongitude());
		}

		BeanUtils.copyProperties(tanque, tanqueAtual, "id");
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

}
