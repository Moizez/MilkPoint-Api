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

import com.milkpointapi.model.Laticinio;
import com.milkpointapi.service.LaticinioService;

@RestController
@RequestMapping("/api")
public class LaticinioResource {

	@Autowired
	private LaticinioService laticinioService;

	@PostMapping("/laticinio")
	public ResponseEntity<Laticinio> add(@RequestBody @Valid Laticinio laticinio) {
		if (laticinio != null) {
			laticinioService.save(laticinio);
			return new ResponseEntity<Laticinio>(laticinio, HttpStatus.CREATED);
		}
		return new ResponseEntity<Laticinio>(laticinio, HttpStatus.CONFLICT);

	}

	@GetMapping("/laticinio")
	public List<Laticinio> listar() {
		return laticinioService.findAll();
	}

	@GetMapping("/laticinio/{id}")
	public ResponseEntity<Laticinio> buscar(@PathVariable Long id) {
		Laticinio Laticinio = laticinioService.findOne(id);

		if (Laticinio == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(Laticinio);
	}

	@PutMapping("/laticinio/{id}")
	public ResponseEntity<Laticinio> update(@PathVariable Long id, @RequestBody Laticinio laticinio) {
		Laticinio laticinioAtual = laticinioService.findOne(id);

		if (laticinioAtual == null)
			return ResponseEntity.notFound().build();

		// DADOS CADASTRAIS
		if (laticinio.getNome() == null || laticinio.getNome().equals(""))
			laticinio.setNome(laticinioAtual.getNome());

		if (laticinio.getNomeFantasia() == null || laticinio.getNomeFantasia().equals(""))
			laticinio.setNomeFantasia(laticinioAtual.getNomeFantasia());

		// if (laticinio.getEmail() == null || laticinio.getEmail().equals(""))
		// laticinio.setEmail(laticinioAtual.getEmail());

		if (laticinio.isStatus() == false)
			laticinio.setStatus(true);
		else
			laticinio.setStatus(false);

		// ENDEREÇO
		if (laticinio.getCep() == null || laticinio.getCep().equals(""))
			laticinio.setCep(laticinioAtual.getCep());

		if (laticinio.getUf() == null || laticinio.getUf().equals(""))
			laticinio.setUf(laticinioAtual.getUf());

		if (laticinio.getLocalidade() == null || laticinio.getLocalidade().equals(""))
			laticinio.setLocalidade(laticinioAtual.getLocalidade());

		if (laticinio.getBairro() == null || laticinio.getBairro().equals(""))
			laticinio.setBairro(laticinioAtual.getBairro());

		if (laticinio.getLogradouro() == null || laticinio.getLogradouro().equals(""))
			laticinio.setLogradouro(laticinioAtual.getLogradouro());

		if (laticinio.getComplemento() == null || laticinio.getComplemento().equals(""))
			laticinio.setComplemento(laticinioAtual.getComplemento());

		BeanUtils.copyProperties(laticinio, laticinioAtual, "id", "password", "email", "descricao", "cnpj",
				"phoneNumber", "dataNascimento", "perfil");

		laticinioAtual = laticinioService.save(laticinioAtual);
		return ResponseEntity.ok(laticinioAtual);

	}

	@DeleteMapping("/laticinio/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Laticinio Laticinio = laticinioService.findOne(id);

		if (Laticinio == null) {
			return ResponseEntity.notFound().build();
		}
		laticinioService.delete(id);
		return ResponseEntity.noContent().build();

	}

}
