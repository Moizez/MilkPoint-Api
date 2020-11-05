package com.milkpointapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.milkpointapi.model.Produtor;

@Repository
public interface ProdutorRepository extends JpaRepository<Produtor, Long> {

	@Query
	public Produtor findByEmailIgnoreCaseContaining(String email);

	@Query
	public Produtor findByEmailAndPassword(String email, String senha);

	@Query(value = "select * from produtor p where p.id =?", nativeQuery = true)
	public Produtor getOne(Long id);

	@Query(value = "select * from produtor p where p.apelido like concat('%', ?, '%')\n"
			+ "	|| p.nome like concat('%', ?, '%')\n" + " || p.cpf like concat('%', ?, '%')\n"
			+ " || p.email like concat('%', ?, '%')\n" + ";", nativeQuery = true)
	public List<Produtor> findByNomeOrApelidoOrCpfOrEmailLike(String nome, String apelido, String cpf, String email);
}
