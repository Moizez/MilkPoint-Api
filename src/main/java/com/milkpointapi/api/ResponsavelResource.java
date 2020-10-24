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

import com.milkpointapi.model.Responsavel;
import com.milkpointapi.model.Tanque;
import com.milkpointapi.service.ResponsavelService;

@RestController
@RequestMapping("/api")
public class ResponsavelResource {

	@Autowired
	private ResponsavelService responsavelService;

	@PostMapping("/responsavel")
	public ResponseEntity<Responsavel> add(@RequestBody @Valid Responsavel responsavel) {
		if (responsavel != null) {
			responsavelService.save(responsavel);
			return new ResponseEntity<Responsavel>(responsavel, HttpStatus.CREATED);
		}
		return new ResponseEntity<Responsavel>(responsavel, HttpStatus.CONFLICT);

	}

	@GetMapping("/responsavel")
	public List<Responsavel> listar() {
		return responsavelService.findAll();
	}

	@GetMapping("/responsavel/{id}")
	public ResponseEntity<Responsavel> buscar(@PathVariable Long id) {
		Responsavel responsavel = responsavelService.findOne(id);
		if (responsavel == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(responsavel);
	}

	@GetMapping("/responsavel/{id}/tanque")
	public ResponseEntity<List<Tanque>> tanques(@PathVariable Long id) {
		Responsavel responsavel = responsavelService.findOne(id);
		if (responsavel == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(responsavel.getTanque());
	}

	@PutMapping("responsavel/{id}")
	public ResponseEntity<Responsavel> update(@PathVariable Long id, @RequestBody Responsavel responsavel) {
		Responsavel responsavelAtual = responsavelService.findOne(id);

		if (responsavelAtual == null) {
			return ResponseEntity.notFound().build();
		}

		BeanUtils.copyProperties(responsavel, responsavelAtual, "id", "password", "cpf", "email", "nome", "descricao", "apelido",
				"cpf", "phoneNumber", "cep", "logradouro", "complemento", "bairro", "localidade", "uf",
				"dataNascimento", "perfil");
		
		responsavelAtual = responsavelService.save(responsavelAtual);
		return ResponseEntity.ok(responsavelAtual);
	}

	@DeleteMapping("responsavel/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Responsavel responsavel = responsavelService.findOne(id);

		if (responsavel == null) {
			return ResponseEntity.notFound().build();
		}
		responsavelService.delete(id);
		return ResponseEntity.noContent().build();

	}

}
