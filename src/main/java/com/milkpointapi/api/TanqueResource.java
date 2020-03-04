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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
		Tanque prod = tanqueService.findOne(id);

		if (prod == null) {
			return ResponseEntity.notFound().build();
		}

		BeanUtils.copyProperties(tanque, prod, "id");
		prod = tanqueService.save(prod);
		return ResponseEntity.ok(prod);

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
	@PutMapping("/tanque/{id}/locationupdate")
	public ResponseEntity<Tanque> tanque(@RequestParam("latitude") float latitude,
			@RequestParam("longitude") float longitude,
			@RequestParam("idResp") Long idResp, @RequestParam("idTanque") Long idTanque) {
		Tanque tanque = tanqueService.findOne(idTanque);
		if (tanque == null || tanque.responsavel.getId() != idResp) {
			return ResponseEntity.notFound().build();
		} else {
			tanque.latidude = latitude;
			tanque.longitude = longitude;
			tanque = tanqueService.save(tanque);
			return ResponseEntity.ok(tanque);
		}
		
	}

}
