package com.milkpointapi.controller;

import java.io.IOException;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.milkpointapi.enums.Capacidade;
import com.milkpointapi.enums.Tipo;
import com.milkpointapi.model.Tanque;
import com.milkpointapi.service.ResponsavelService;
import com.milkpointapi.service.TanqueService;

@Controller
@RequestMapping("/tanque")
public class TanqueController {

	@Autowired
	private TanqueService tanqueService;

	@Autowired
	private ResponsavelService responsavelService;

	@GetMapping("/add")
	public ModelAndView add(Tanque tanque) {

		ModelAndView mv = new ModelAndView("tanque/form");
		mv.addObject("responsaveis", responsavelService.findAll());
		mv.addObject("tanque", tanque);
		mv.addObject("tipos", Tipo.values());

		return mv;
	}

	@PostMapping("/save")
	public ModelAndView save(@Valid Tanque tanque, BindingResult result) throws IOException {

		try {
			if (result.hasErrors()) {
				return add(tanque);

			} else if (tanque.getCapacidade() == Capacidade.MIL) {
				tanque.setQtdRestante(1000 - tanque.getQtdAtual());
			} else if (tanque.getCapacidade() == Capacidade.DOISMIL) {
				tanque.setQtdRestante(2000 - tanque.getQtdAtual());
			} else if (tanque.getCapacidade() == Capacidade.TRESMIL) {
				tanque.setQtdRestante(3000 - tanque.getQtdAtual());
			} else if (tanque.getCapacidade() == Capacidade.QUATROMIL) {
				tanque.setQtdRestante(4000 - tanque.getQtdAtual());
			} else if (tanque.getCapacidade() == Capacidade.QUATROMILEQUINHENTOS) {
				tanque.setQtdRestante(4500 - tanque.getQtdAtual());
			}

			tanque.setDataCriacao(new Date());
			tanqueService.save(tanque);
			return findAll();

		} catch (

		Exception e) {
			return new ModelAndView("tanque/form").addObject("tanque", tanque).addObject("falha",
					"Já existe um tanque com este nome, escolha outro.");
		}
	}

	@GetMapping("/list")
	public ModelAndView findAll() {

		ModelAndView mv = new ModelAndView("tanque/list");
		mv.addObject("tanques", tanqueService.findAll());

		return mv;
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		return add(tanqueService.findOne(id));
	}

	@GetMapping("/details/{id}")
	public ModelAndView details(@PathVariable("id") Long id) {

		ModelAndView mv = new ModelAndView("tanque/detalhes");
		mv.addObject("tanque", tanqueService.findOne(id));
		return mv;
	}

	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		tanqueService.delete(id);
		return findAll();
	}

	@GetMapping("/buscar/nome")
	public ModelAndView findByTanque(@RequestParam("nome") String buscar) {
		ModelAndView mv = new ModelAndView("tanque/list");

		final String nome = buscar;
		final String localidade = buscar;
		final String uf = buscar;

		mv.addObject("tanques", tanqueService.searchFor(nome, localidade, uf));
		return mv;
	}

	@ModelAttribute("tipos")
	public Tipo[] getTipos() {
		return Tipo.values();
	}

	@ModelAttribute("capacidades")
	public Capacidade[] getCapacidades() {
		return Capacidade.values();
	}

}
