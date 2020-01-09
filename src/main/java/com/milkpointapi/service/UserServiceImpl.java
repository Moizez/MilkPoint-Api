package com.milkpointapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.milkpointapi.model.UserInfo;
import com.milkpointapi.repository.UserRepository;
import com.milkpointapi.utils.PasswordUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserInfo save(UserInfo userInfo) {
		userInfo.setEnabled(true);
		if (StringUtils.hasText(userInfo.getPassword())) {
			userInfo.setPassword(PasswordUtil.getEncodePassword(userInfo.getPassword()));

		}

		return userRepository.save(userInfo);
	}

	@Override
	public UserInfo findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void update(UserInfo dbUser) {
		userRepository.save(dbUser);
	}

	@Override
	public UserInfo getOne(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public UserInfo findByRoleAdmin() {
		return userRepository.findByRoleAdmin();
	}

	@Override
	public Object findAll() {
		return userRepository.findAll();
	}

	@Override
	public List<UserInfo> findByNome(String firstName) {
		return userRepository.findByFirstNameIgnoreCaseContaining(firstName);
	}

}
