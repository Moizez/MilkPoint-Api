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

import com.milkpointapi.model.Produtor;
import com.milkpointapi.service.ProdutorService;

@RestController
@RequestMapping("/api")
public class ProdutorResource {

	@Autowired
	private ProdutorService produtorService;

	@PostMapping("/produtor")
	public ResponseEntity<Produtor> add(@RequestBody @Valid Produtor produtor) {
		if (produtor != null) {
			produtorService.save(produtor);
			return new ResponseEntity<Produtor>(produtor, HttpStatus.CREATED);
		}
		return new ResponseEntity<Produtor>(produtor, HttpStatus.CONFLICT);

	}

	@GetMapping("/produtor")
	public List<Produtor> listar() {
		return produtorService.findAll();
	}

	@GetMapping("/produtor/{id}")
	public ResponseEntity<Produtor> buscar(@PathVariable Long id) {
		Produtor produtor = produtorService.findOne(id);

		if (produtor == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(produtor);
	}

	private boolean isObjectNull(Produtor prod) {
		if (prod.getNome() == null && prod.getCpf() == null && prod.getEmail() == null)
			return true;
		return false;
	}

	@PutMapping("produtor/{id}")
	public ResponseEntity<Produtor> update(@PathVariable Long id, @RequestBody Produtor produtor) {
		Produtor prod = produtorService.findOne(id);

		if (prod == null) {
			return ResponseEntity.notFound().build();
		}

		if (isObjectNull(produtor)) {
			prod.setStatus(produtor.isStatus());
		} else
			BeanUtils.copyProperties(produtor, prod, "id");

		System.out.println("STATUS: " + prod.isStatus());

		prod = produtorService.save(prod);
		return ResponseEntity.ok(prod);
	}

	@DeleteMapping("produtor/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Produtor produtor = produtorService.findOne(id);

		if (produtor == null) {
			return ResponseEntity.notFound().build();
		}
		produtorService.delete(id);
		return ResponseEntity.noContent().build();

	}

}