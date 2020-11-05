package com.milkpointapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.milkpointapi.model.Tanque;

@Repository
public interface TanqueRepository extends JpaRepository<Tanque, Long> {

	@Query(value = "select * from tanque t where t.nome like concat('%', ?, '%')\n"
			+ "	|| t.localidade like concat('%', ?, '%')\n" + " || t.uf like concat('%', ?, '%')\n"
			+ ";", nativeQuery = true)
	public List<Tanque> findByNomeOrLocalidadeOrUfLike(String nome, String localidade, String uf);

	@Query
	public Tanque findByNome(String nome);

	@Query(value = "SELECT * FROM tanque t where t.status = 1", nativeQuery = true)
	public List<Tanque> buscaTanqueAtivos();

	@Query(value = "SELECT * FROM tanque t where t.status = 0", nativeQuery = true)
	public List<Tanque> buscaTanqueInativos();

	@Query(value = "SELECT * FROM tanque t where (t.tecnico_id like concat('%', :id, '%'))\n"
			+ "and t.status = 1", nativeQuery = true)
	public List<Tanque> buscaTanquesAtivosPorTecnico(@Param("id") Long id);

	@Query(value = "SELECT * FROM tanque t where (t.tecnico_id like concat('%', :id, '%'))\n"
			+ "and t.status = 0", nativeQuery = true)
	public List<Tanque> buscaTanquesInativosPorTecnico(@Param("id") Long id);

}
