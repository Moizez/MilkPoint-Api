package com.milkpointapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.milkpointapi.model.Laticinio;

@Repository
public interface LaticinioRepository extends JpaRepository<Laticinio, Long> {

	@Query
	public Laticinio findByEmailIgnoreCaseContaining(String email);
	@Query
	public Laticinio findByEmailAndPassword(String email, String senha);
	
	@Query(value = "select * from laticinio l where l.id = ?", nativeQuery = true)
	public Laticinio getOne(Long id);

	@Query(value = "select * from laticinio l where l.nome_fantasia like concat('%', ?, '%')\n"
			+ "	|| l.nome like concat('%', ?, '%')\n" 
			+ " || l.cnpj like concat('%', ?, '%')\n"
			+ " || l.email like concat('%', ?, '%')\n" + ";", nativeQuery = true)
	public List<Laticinio> findByNomeOrApelidoOrCpfOrEmailLike(String nome, String nomeFantasia, String cnpj, String email);
}
