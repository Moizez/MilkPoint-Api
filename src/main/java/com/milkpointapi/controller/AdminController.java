package com.milkpointapi.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.milkpointapi.model.UserInfo;
import com.milkpointapi.service.DepositoService;
import com.milkpointapi.service.LaticinioService;
import com.milkpointapi.service.ProdutorService;
import com.milkpointapi.service.ResponsavelService;
import com.milkpointapi.service.RetiradaService;
import com.milkpointapi.service.TanqueService;
import com.milkpointapi.service.TecnicoService;
import com.milkpointapi.service.UserService;

@Controller
public class AdminController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TanqueService tanqueService;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private DepositoService depositoService;
	
	@Autowired
	private RetiradaService retiradaService;

	@Autowired
	private ResponsavelService responsavelService;

	@Autowired
	private ProdutorService produtorService;

	@Autowired
	private LaticinioService laticinioService;
	
	@PostMapping(value = "/register")
	public String registration(@ModelAttribute UserInfo userInfo, HttpServletRequest request, Model model) {
		// String password = userInfo.getPassword();
		userInfo.setRole("ADMIN");
		UserInfo dbUser = userService.save(userInfo);
		// securityService.autoLogin(dbUser.getEmail(), password, dbUser.getRole(),
		// request);
		model.addAttribute("user", dbUser);

		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities();
		Iterator<? extends GrantedAuthority> iterator = grantedAuthorities.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println(name);
		return "redirect:/redirectdashboard";
	}

	@RequestMapping("/add")
	public ModelAndView add(UserInfo user) {
		ModelAndView mv = new ModelAndView("admin/register");
		mv.addObject("user", user);
		return mv;
	}

	@GetMapping("/edit/{id}")
	private ModelAndView edit(@PathVariable("id") Long id) {
		UserInfo user = userService.getOne(id);
		return add(user);
	}

	@GetMapping("/admin/list")
	private ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("admin/list");
		mv.addObject("users", userService.findAll());
		return mv;
	}

	@GetMapping("/buscar/nome")
	public ModelAndView findByAdmin(@RequestParam("firstName") String nome) {
		ModelAndView mv = new ModelAndView("admin/list");
		mv.addObject("users", userService.findByNome(nome));
		return mv;
	}
	
	@RequestMapping("/dashboard")
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("dashboard");
		mv.addObject("tanques", tanqueService.findAll());
		mv.addObject("tecnicos", tecnicoService.findAll());
		mv.addObject("responsaveis", responsavelService.findAll());
		mv.addObject("produtores", produtorService.findAll());
		mv.addObject("laticinios", laticinioService.findAll());
		mv.addObject("depositos", depositoService.buscaPendentes());
		mv.addObject("retiradas", retiradaService.buscaPendentes());
		return mv;
	}
	
	@GetMapping("/admin/changepassword")
	private ModelAndView changePassWord() {
		return new ModelAndView("admin/changepassword");
	}
	
	@PostMapping("/admin/changepassword")
	private ModelAndView changePassWord(
			Principal principal,
			@RequestParam String password
			) {	

		UserInfo user = userService.findByEmail(principal.getName());
		user.setPassword(password);
		userService.save(user);
		ModelAndView mv = new ModelAndView("admin/changepassword");
		mv
			.addObject("alerta", "success")
			.addObject("texto", "Senha alterada com Sucesso.");
		return mv;
	}
}
