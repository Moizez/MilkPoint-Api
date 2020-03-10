package com.milkpointapi.api;

import java.util.ArrayList;
import java.util.Comparator;
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

import com.milkpointapi.model.Retirada;
import com.milkpointapi.model.Deposito;
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
	
	@GetMapping("/responsavel/{id}/tanques")
	public ResponseEntity<List<Tanque>> tanques(@PathVariable Long id) {
		Responsavel responsavel = responsavelService.findOne(id);

		if (responsavel == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(responsavel.getTanque());
	}

	@GetMapping("/responsavel/{id}/notificacoes")
	public ResponseEntity<List<Notificacao>> notificacoes(@PathVariable Long id) {
		Responsavel responsavel = responsavelService.findOne(id);
		if (responsavel == null) {
			return ResponseEntity.notFound().build();
		}
		List<Tanque> tanques = responsavel.getTanque();
		List<Notificacao> notificacoes = new ArrayList<>();
		tanques.forEach(tanque -> {
			List<Deposito> depositos = tanque.getDepositos();
			for(Deposito deposito : depositos){
				if (deposito.getConfirmacao() == false) { 
					Notificacao notificacao = new Notificacao(); 
					BeanUtils.copyProperties(deposito, notificacao);
					notificacoes.add(notificacao);
				}
			}
			List<Retirada> retiradas = tanque.getRetiradas();
			for(Retirada retirada : retiradas){
				if (retirada.getConfirmacao() == false) { 
					Notificacao notificacao = new Notificacao(); 
					BeanUtils.copyProperties(retirada, notificacao);
					notificacoes.add(notificacao);
				}
			}
		});
		
		return ResponseEntity.ok(notificacoes);
	}
	
	//Ordenador de objetos - QuickSort
	int partition(ArrayList<Notificacao> list, int low, int high) 
    { 
        Notificacao pivot = list.get(high);  
        int i = (low-1); 
        for (int j=low; j<high; j++) 
        { 
            if (list.get(j).getId() > pivot.getId()) 
            { 
                i++; 
                Notificacao temp = list.get(i); 
                list.set(i, list.get(j)); 
                list.set(j,temp); 
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        Notificacao temp = list.get(i+1); 
        list.set(i+1, list.get(high)); 
        list.set(high,temp); 
        
        return i+1; 
    } 
  
  
    /* The main function that implements QuickSort() 
      arr[] --> Array to be sorted, 
      low  --> Starting index, 
      high  --> Ending index */
    void sort(int arr[], int low, int high) 
    { 
        if (low < high) 
        { 
            /* pi is partitioning index, arr[pi] is  
              now at right place */
            int pi = partition(arr, low, high); 
  
            // Recursively sort elements before 
            // partition and after partition 
            sort(arr, low, pi-1); 
            sort(arr, pi+1, high); 
        } 
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
