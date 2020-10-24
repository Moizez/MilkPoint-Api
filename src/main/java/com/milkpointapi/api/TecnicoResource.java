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

import com.milkpointapi.model.Tanque;
import com.milkpointapi.model.Tecnico;
import com.milkpointapi.service.TecnicoService;

@RestController
@RequestMapping("/api")
public class TecnicoResource {

	@Autowired
	private TecnicoService tecnicoService;

	@PostMapping("/tecnico")
	public ResponseEntity<Tecnico> add(@RequestBody @Valid Tecnico tecnico) {
		if (tecnico != null) {
			tecnicoService.save(tecnico);
			return new ResponseEntity<Tecnico>(tecnico, HttpStatus.CREATED);
		}
		return new ResponseEntity<Tecnico>(tecnico, HttpStatus.CONFLICT);

	}

	@GetMapping("/tecnico")
	public List<Tecnico> listar() {
		return tecnicoService.findAll();
	}

	@GetMapping("/tecnico/{id}")
	public ResponseEntity<Tecnico> buscar(@PathVariable Long id) {
		Tecnico tecnico = tecnicoService.findOne(id);

		if (tecnico == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(tecnico);
	}
	
	@GetMapping("/tecnico/{id}/tanque")
	public ResponseEntity<List<Tanque>> tanques(@PathVariable Long id) {
		Tecnico tecnico = tecnicoService.findOne(id);
		if (tecnico == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(tecnico.getTanques());
	}

	@PutMapping("tecnico/{id}")
	public ResponseEntity<Tecnico> update(@PathVariable Long id, @RequestBody Tecnico tecnico) {
		Tecnico tec = tecnicoService.findOne(id);

		if (tec == null) {
			return ResponseEntity.notFound().build();
		}

		BeanUtils.copyProperties(tecnico, tec, "id");
		tec = tecnicoService.save(tec);
		return ResponseEntity.ok(tec);
	}

	@DeleteMapping("tecnico/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Tecnico tecnico = tecnicoService.findOne(id);

		if (tecnico == null) {
			return ResponseEntity.notFound().build();
		}
		tecnicoService.delete(id);
		return ResponseEntity.noContent().build();

	}

}