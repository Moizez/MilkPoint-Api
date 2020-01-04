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
import org.springframework.web.servlet.ModelAndView;

import com.milkpointapi.model.Produtor;
import com.milkpointapi.service.ProdutorService;

@Controller
@RequestMapping("/produtor")
public class ProdutorController {

	@Autowired
	private ProdutorService produtorService;

	@RequestMapping("/add")
	public ModelAndView add(Produtor produtor) {
		ModelAndView mv = new ModelAndView("produtor/form");
		mv.addObject("produtor", produtor);
		return mv;
	}

	@PostMapping("/save")
	public ModelAndView save(@Valid Produtor produtor, BindingResult result) throws IOException {

		if (result.hasErrors()) {
			return add(produtor);
		}
		produtorService.save(produtor);

		return findAll();
	}

	@GetMapping("/listar")
	private ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("produtor/listar");
		mv.addObject("produtores", produtorService.findAll());
		return mv;
	}

	@GetMapping("/edit/{id}")
	private ModelAndView edit(@PathVariable("id") Long id) {
		Produtor produtor = produtorService.findOne(id);
		return add(produtor);
	}

	@GetMapping("/delete/{id}")
	private ModelAndView delete(@PathVariable("id") Long id) {
		produtorService.delete(id);
		return findAll();
	}

}
