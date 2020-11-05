package com.milkpointapi.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.milkpointapi.model.Tecnico;
import com.milkpointapi.service.TecnicoService;

@Controller
@RequestMapping("/tecnico")
public class TecnicoController {

	@Autowired
	private TecnicoService tecnicoService;

	@RequestMapping("/add")
	public ModelAndView add(Tecnico tecnico) {
		ModelAndView mv = new ModelAndView("tecnico/form");
		mv.addObject("tecnico", tecnico);
		return mv;
	}

	@PostMapping("/save")
	public ModelAndView save(@Valid Tecnico tecnico, BindingResult result) throws IOException {

		try {
			if (result.hasErrors()) {
				return add(tecnico);
			} else {
				tecnicoService.save(tecnico);
				return findAll();
			}
		} catch (Exception e) {
			return new ModelAndView("tecnico/form").addObject("tecnico", tecnico).addObject("falha",
					"E-mail ou CPF já existentem!");
		}
	}

	@GetMapping("/list")
	private ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("tecnico/list");
		mv.addObject("tecnicos", tecnicoService.findAll());
		return mv;
	}

	@GetMapping("/edit/{id}")
	private ModelAndView edit(@PathVariable("id") Long id) {
		Tecnico tecnico = tecnicoService.findOne(id);
		return add(tecnico);
	}

	@GetMapping("/delete/{id}")
	private ModelAndView delete(@PathVariable("id") Long id) {
		tecnicoService.delete(id);
		return findAll();
	}

	@GetMapping("/buscar/nome")
	public ModelAndView findByTecnico(@RequestParam("nome") String buscar) {
		ModelAndView mv = new ModelAndView("tecnico/list");

		final String nome = buscar;
		final String apelido = buscar;
		final String cpf = buscar;
		final String email = buscar;

		mv.addObject("tecnicos", tecnicoService.searchFor(nome, apelido, cpf, email));
		return mv;
	}

}
