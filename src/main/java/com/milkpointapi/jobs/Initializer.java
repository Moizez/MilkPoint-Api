package com.milkpointapi.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.milkpointapi.model.UserInfo;
import com.milkpointapi.service.UserService;

@Component
public class Initializer implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserService userService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("----- Criando Usuário ------");
		createUserAdmin();

		System.out.println("----- Usuário Criado com Sucesso! -----");
	}

	private void createUserAdmin() {
		UserInfo usuario = userService.findByRoleAdmin();

		if (usuario == null) {
			usuario = new UserInfo();
			usuario.setFirstName("Moisés");
			usuario.setLastName("Henrique");
			usuario.setEmail("moizez@gmail.com");
			usuario.setPassword("123");
			usuario.setRole("ADMIN");
			userService.save(usuario);

		}

	}
}
