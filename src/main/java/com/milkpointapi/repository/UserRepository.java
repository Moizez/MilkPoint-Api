package com.milkpointapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.milkpointapi.model.UserInfo;

@Repository
public interface UserRepository extends CrudRepository<UserInfo, Long> {

	UserInfo findByEmailAndEnabled(String email, boolean enabled);

	UserInfo findByEmail(String email);

	Optional<UserInfo> findById(Long id);

	@Query(value = "SELECT * FROM user u where u.role = \"ADMIN\";", nativeQuery = true)
	UserInfo findByRoleAdmin();

}
