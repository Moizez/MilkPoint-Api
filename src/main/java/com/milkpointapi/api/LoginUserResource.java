package com.milkpointapi.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.milkpointapi.service.LoginUserService;

@RestController
@RequestMapping("/api")
public class LoginUserResource {

	@Autowired
	LoginUserService service;

	@PostMapping("/login")
	public ResponseEntity<Object> login(String email, String senha) {
		if (email != null && senha != null) {
			Object obj = service.login(email, senha);

			return new ResponseEntity<Object>(obj, HttpStatus.OK);

		}

		return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	}

}
