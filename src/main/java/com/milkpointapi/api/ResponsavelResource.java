package com.milkpointapi.api;

import java.util.ArrayList;
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

import com.milkpointapi.model.Notificacao;
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
	
	@GetMapping("/responsavel/{id}")
	public ResponseEntity<List<Tanque>> tanques(@PathVariable Long id) {
		Responsavel responsavel = responsavelService.findOne(id);
		if (responsavel == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(responsavel.getTanque());
	}

	@GetMapping("/responsavel/{id}/sms/{value}")
	public Responsavel updateSms(@PathVariable Long id, @PathVariable boolean value) {
		Responsavel responsavel = responsavelService.findOne(id);
		if(value == true) {
			responsavel.setSms(true);
		} else {
			responsavel.setSms(false);
		}
		responsavel = responsavelService.save(responsavel);
		return responsavel;
	}
	
	@GetMapping("/responsavel/{id}/notificacoes")
	public ResponseEntity<List<Notificacao>> notificacoes(@PathVariable Long id) {
		Responsavel responsavel = responsavelService.findOne(id);
		if (responsavel == null) {
			return ResponseEntity.notFound().build();
		}
		List<Notificacao> notificacoes = new ArrayList<>();
		responsavel.getTanque().forEach(tanque -> {
			tanque.getDepositos().forEach(deposito ->{
				if(deposito.getConfirmacao() == false && 
						deposito.getExcluido() == false) {   
					Notificacao notificacao = new Notificacao();
					notificacao.setTipo("DEPÓSITO");
					notificacao.setQuantidade(deposito.getQuantidade());
					notificacao.setTanque(deposito.getTanque().getNome());
					notificacao.setSolicitante(deposito.getProdutor().getNome());
					notificacoes.add(notificacao);
				}
			});
			tanque.getRetiradas().forEach(retirada ->{
				if(retirada.getConfirmacao() == false && 
						retirada.getExcluido() == false) {   
					Notificacao notificacao = new Notificacao();
					notificacao.setTipo("RETIRADA");
					notificacao.setQuantidade(retirada.getQuantidade());
					notificacao.setTanque(retirada.getTanque().getNome());
					notificacao.setSolicitante(retirada.getLaticinio().getNome());
					notificacoes.add(notificacao);
				}
			});
		});
		return ResponseEntity.ok(notificacoes);
	}

	@PutMapping("responsavel/{id}")
	public ResponseEntity<Responsavel> update(@PathVariable Long id, @RequestBody Responsavel responsavel) {
		Responsavel resp = responsavelService.findOne(id);

		if (resp == null) {
			return ResponseEntity.notFound().build();
		}

		BeanUtils.copyProperties(responsavel, resp, "id");
		resp = responsavelService.save(resp);
		return ResponseEntity.ok(resp);
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
