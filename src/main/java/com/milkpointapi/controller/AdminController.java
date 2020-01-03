package com.milkpointapi.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.milkpointapi.model.UserInfo;
import com.milkpointapi.service.UserService;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/dashboard")
	public String admindashboard(Principal principal, Model model) {
		UserInfo dbUser = userService.findByEmail(principal.getName());
		model.addAttribute("user", dbUser);
		return "/home";
	}

}
