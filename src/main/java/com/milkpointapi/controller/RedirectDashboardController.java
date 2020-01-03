package com.milkpointapi.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import com.milkpointapi.model.UserInfo;
import com.milkpointapi.service.UserService;

@Controller
public class RedirectDashboardController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/redirectdashboard")
	public String redirectdashboard(Principal principal) {
		String redirecturl = "";
		UserInfo dbUser = userService.findByEmail(principal.getName());
		if (dbUser != null && StringUtils.hasText(dbUser.getRole())) {
			if (dbUser.getRole().equalsIgnoreCase("ADMIN")) {
				redirecturl = "redirect:/home";
			}
		}
		return redirecturl;
	}

}
