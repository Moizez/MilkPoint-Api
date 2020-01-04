package com.milkpointapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.milkpointapi.model.UserInfo;
import com.milkpointapi.service.SessionService;

@Controller
public class HomeController {

	@Autowired
	private SessionService<UserInfo> sessionService;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("user", new UserInfo());
		return "index";
	}

	@RequestMapping("/home")
	public String home() {
		return "home";
	}

	@GetMapping("/loginfailure")
	public String loginfailure(ModelMap model) {
		model.addAttribute("user", new UserInfo());
		model.addAttribute("alerta", "erro");
		model.addAttribute("titulo", "Credenciais inválidas!");
		model.addAttribute("texto", "Login ou senha incorretos, tente novamente.");
		return "index";
	}

	@GetMapping("/logout")
	public String logout() {
		sessionService.clearSession();
		return "index";
	}

}
