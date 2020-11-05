package com.milkpointapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.milkpointapi.model.Responsavel;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {

	@Query
	public Responsavel findByEmailIgnoreCaseContaining(String email);
	@Query
	public Responsavel findByEmailAndPassword(String email, String senha);
	
	@Query(value = "select * from responsavel r where r.apelido like concat('%', ?, '%')\n"
			+ "	|| r.nome like concat('%', ?, '%')\n" 
			+ " || r.cpf like concat('%', ?, '%')\n"
			+ " || r.email like concat('%', ?, '%')\n" + ";", nativeQuery = true)
	public List<Responsavel> findByNomeOrApelidoOrCpfOrEmailLike(String nome, String apelido, String cpf, String email);

}
