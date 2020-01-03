package com.milkpointapi.service;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.*;

import com.milkpointapi.model.UserInfo;
import com.milkpointapi.repository.UserRepository;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserInfo user = userRepository.findByEmailAndEnabled(email, true);
		if (user == null) {
			throw new UsernameNotFoundException("Usuário não encontrado " + email);

		}

		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole());
		UserDetails userDetails = (UserDetails) new User(user.getEmail(), user.getPassword(), Arrays.asList(authority));

		return userDetails;
	}

}
