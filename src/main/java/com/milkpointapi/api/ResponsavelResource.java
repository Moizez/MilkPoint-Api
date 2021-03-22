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

import com.milkpointapi.model.Responsavel;
import com.milkpointapi.model.Tanque;
import com.milkpointapi.service.ResponsavelService;

@RestController
@RequestMapping("/api")
public class ResponsavelResource {

	@Autowired
	private ResponsavelService responsavelService;

	@PostMapping("/responsavel")
	public ResponseEntity<Responsavel> add(@RequestBody @Validated Responsavel responsavel) {
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

		if (responsavelAtual == null)
			return ResponseEntity.notFound().build();

		// DADOS CADASTRAIS
		if (responsavel.getNome() == null || responsavel.getNome().equals(""))
			responsavel.setNome(responsavelAtual.getNome());

		if (responsavel.getApelido() == null || responsavel.getApelido().equals(""))
			responsavel.setApelido(responsavelAtual.getApelido());

		// if (responsavel.getEmail() == null || responsavel.getEmail().equals(""))
		// responsavel.setEmail(responsavelAtual.getEmail());

		if (responsavel.isStatus() == false)
			responsavel.setStatus(false);
		else
			responsavel.setStatus(true);

		// ENDEREÇO
		if (responsavel.getCep() == null || responsavel.getCep().equals(""))
			responsavel.setCep(responsavelAtual.getCep());

		if (responsavel.getUf() == null || responsavel.getUf().equals(""))
			responsavel.setUf(responsavelAtual.getUf());

		if (responsavel.getLocalidade() == null || responsavel.getLocalidade().equals(""))
			responsavel.setLocalidade(responsavelAtual.getLocalidade());

		if (responsavel.getBairro() == null || responsavel.getBairro().equals(""))
			responsavel.setBairro(responsavelAtual.getBairro());

		if (responsavel.getLogradouro() == null || responsavel.getLogradouro().equals(""))
			responsavel.setLogradouro(responsavelAtual.getLogradouro());

		if (responsavel.getComplemento() == null || responsavel.getComplemento().equals(""))
			responsavel.setComplemento(responsavelAtual.getComplemento());

		BeanUtils.copyProperties(responsavel, responsavelAtual, "id", "password", "cpf", "email", "descricao", "cpf",
				"phoneNumber", "dataNascimento", "perfil");

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
