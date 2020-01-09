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

import com.milkpointapi.model.Responsavel;
import com.milkpointapi.service.ResponsavelService;

@Controller
@RequestMapping("/responsavel")
public class ResponsavelController {

	@Autowired
	private ResponsavelService responsavelService;

	@GetMapping("/add")
	public ModelAndView add(Responsavel responsavel) {
		ModelAndView mv = new ModelAndView("responsavel/form");
		mv.addObject("responsavel", responsavel);
		return mv;
	}

	@PostMapping("/save")
	public ModelAndView save(@Valid Responsavel responsavel, BindingResult result) throws IOException {

		try {
			if (result.hasErrors()) {
				return add(responsavel);
			} else {
				responsavelService.save(responsavel);
				return findAll();
			}
		} catch (Exception e) {
			return new ModelAndView("responsavel/form").addObject("responsavel", responsavel).addObject("falha",
					"E-mail ou CPF já existentem!");
		}
	}

	@GetMapping("/list")
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("responsavel/list");
		mv.addObject("responsaveis", responsavelService.findAll());
		return mv;
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		return add(responsavelService.findOne(id));
	}

	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		responsavelService.delete(id);
		return findAll();
	}

	@GetMapping("/buscar/nome")
	public ModelAndView findByResponsavel(@RequestParam("nome") String nome) {
		ModelAndView mv = new ModelAndView("responsavel/list");
		mv.addObject("responsaveis", responsavelService.findByNome(nome));
		return mv;
	}

}
