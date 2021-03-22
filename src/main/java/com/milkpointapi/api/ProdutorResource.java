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

import com.milkpointapi.model.Produtor;
import com.milkpointapi.service.ProdutorService;

@RestController
@RequestMapping("/api")
public class ProdutorResource {

	@Autowired
	private ProdutorService produtorService;

	@PostMapping("/produtor")
	public ResponseEntity<Produtor> add(@RequestBody @Validated Produtor produtor) {
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

	@PutMapping("produtor/{id}")
	public ResponseEntity<Produtor> update(@PathVariable Long id, @RequestBody Produtor produtor) {
		Produtor produtorAtual = produtorService.findOne(id);
		
		if (produtorAtual == null)
			return ResponseEntity.notFound().build();

		// DADOS CADASTRAIS
		if (produtor.getNome() == null || produtor.getNome().equals(""))
			produtor.setNome(produtorAtual.getNome());

		if (produtor.getApelido() == null || produtor.getApelido().equals(""))
			produtor.setApelido(produtorAtual.getApelido());

		// if (produtor.getEmail() == null || produtor.getEmail().equals(""))
		// produtor.setEmail(produtorAtual.getEmail());

		if (produtor.isStatus() == false)
			produtor.setStatus(false);
		else
			produtor.setStatus(true);

		// ENDEREÇO
		if (produtor.getCep() == null || produtor.getCep().equals(""))
			produtor.setCep(produtorAtual.getCep());

		if (produtor.getUf() == null || produtor.getUf().equals(""))
			produtor.setUf(produtorAtual.getUf());

		if (produtor.getLocalidade() == null || produtor.getLocalidade().equals(""))
			produtor.setLocalidade(produtorAtual.getLocalidade());

		if (produtor.getBairro() == null || produtor.getBairro().equals(""))
			produtor.setBairro(produtorAtual.getBairro());

		if (produtor.getLogradouro() == null || produtor.getLogradouro().equals(""))
			produtor.setLogradouro(produtorAtual.getLogradouro());

		if (produtor.getComplemento() == null || produtor.getComplemento().equals(""))
			produtor.setComplemento(produtorAtual.getComplemento());

		BeanUtils.copyProperties(produtor, produtorAtual, "id", "password", "descricao", "cpf", "email", "phoneNumber",
				"dataNascimento", "perfil");

		produtorAtual = produtorService.save(produtorAtual);
		return ResponseEntity.ok(produtorAtual);
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