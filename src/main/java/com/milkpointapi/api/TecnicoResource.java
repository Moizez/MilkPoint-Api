package com.milkpointapi.api;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
	public ResponseEntity<Tecnico> add(@RequestBody @Validated Tecnico tecnico) {
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
		Tecnico tecnicoAtual = tecnicoService.findOne(id);
		
		if (tecnicoAtual == null)
			return ResponseEntity.notFound().build();

		// DADOS CADASTRAIS
		if (tecnico.getNome() == null || tecnico.getNome().equals(""))
			tecnico.setNome(tecnicoAtual.getNome());

		if (tecnico.getApelido() == null || tecnico.getApelido().equals(""))
			tecnico.setApelido(tecnicoAtual.getApelido());

		// if (tecnico.getEmail() == null || tecnico.getEmail().equals(""))
		// tecnico.setEmail(tecnicoAtual.getEmail());

		// ENDEREÇO
		if (tecnico.getCep() == null || tecnico.getCep().equals(""))
			tecnico.setCep(tecnicoAtual.getCep());

		if (tecnico.getUf() == null || tecnico.getUf().equals(""))
			tecnico.setUf(tecnicoAtual.getUf());

		if (tecnico.getLocalidade() == null || tecnico.getLocalidade().equals(""))
			tecnico.setLocalidade(tecnicoAtual.getLocalidade());

		if (tecnico.getBairro() == null || tecnico.getBairro().equals(""))
			tecnico.setBairro(tecnicoAtual.getBairro());

		if (tecnico.getLogradouro() == null || tecnico.getLogradouro().equals(""))
			tecnico.setLogradouro(tecnicoAtual.getLogradouro());

		if (tecnico.getComplemento() == null || tecnico.getComplemento().equals(""))
			tecnico.setComplemento(tecnicoAtual.getComplemento());

		BeanUtils.copyProperties(tecnico, tecnicoAtual, "id", "password", "descricao", "cpf", "email", "phoneNumber",
				"dataNascimento", "perfil");

		tecnicoAtual = tecnicoService.save(tecnicoAtual);
		return ResponseEntity.ok(tecnicoAtual);
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