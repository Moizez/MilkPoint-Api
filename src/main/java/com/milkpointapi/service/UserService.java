package com.milkpointapi.service;

import com.milkpointapi.model.UserInfo;

public interface UserService {

	UserInfo save(UserInfo userInfo);

	UserInfo findByEmail(String email);

	public UserInfo getOne(Long id);

	void update(UserInfo userInfo);

	public UserInfo findByRoleAdmin();

	Object findAll();

}
