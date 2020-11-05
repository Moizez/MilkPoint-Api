package com.milkpointapi.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.milkpointapi.model.UserInfo;
import com.milkpointapi.service.LoginUserService;

@RestController
@RequestMapping("/api")
public class LoginUserResource {

	@Autowired
	private LoginUserService service;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserInfo info) {
		if (info.getEmail() != null && info.getPassword() != null) {
			Object obj = service.login(info.getEmail(), info.getPassword());
			if (obj != null) {
				return new ResponseEntity<Object>(obj, HttpStatus.OK);
			}
		}

		return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	}
}