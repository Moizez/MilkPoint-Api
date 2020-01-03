package com.milkpointapi.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
	
	static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public static String getEncodePassword(String password) {
		return passwordEncoder.encode(password);
	}

}
