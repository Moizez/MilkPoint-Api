package com.milkpointapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.milkpointapi.model.Retirada;

@Repository
public interface RetiradaRepository extends JpaRepository<Retirada, Long> {
	
	@Query(value = "select * from retirada r where r.excluido = 0 and r.confirmacao = 0 ORDER BY data_now ASC;", nativeQuery = true)
	public List<Retirada> buscaPendentes();
	
	@Query(value = "SELECT * FROM retirada r \n" + "inner join laticinio l on (r.retirada_laticinio = l.id)\n"
			+ "where (r.excluido = 0 and r.confirmacao = 0) and (l.id like concat ('%', :id, '%'))ORDER BY data_now DESC", nativeQuery = true)
	public List<Retirada> buscaPendentesPorLaticinio(@Param("id") Long id);

	@Query(value = "select * from retirada r where r.excluido = 1 or r.confirmacao = 1 ORDER BY data_now DESC", nativeQuery = true)
	public List<Retirada> buscaResolvidos();

	@Query(value = "SELECT * FROM retirada r \n" + "inner join laticinio l on (r.retirada_laticinio = l.id)\n"
			+ "where (r.confirmacao = 1) and (l.id like concat ('%', :id, '%'))ORDER BY data_now DESC", nativeQuery = true)
	public List<Retirada> buscaConfirmados(@Param("id") Long id);

	@Query(value = "SELECT * FROM retirada r \n" + "inner join laticinio l on (r.retirada_laticinio = l.id)\n"
			+ "where (r.excluido = 1) and (l.id like concat ('%', :id, '%'))ORDER BY data_now DESC", nativeQuery = true)
	public List<Retirada> buscaCancelados(@Param("id") Long id);
	
	@Query(value = "select * from retirada r where r.confirmacao = 1 ORDER BY data_now ASC", nativeQuery = true)
	public List<Retirada> buscaTodosConfirmados();

	@Query(value = "select * from retirada r where r.excluido = 1 ORDER BY data_now ASC", nativeQuery = true)
	public List<Retirada> buscaTodosCancelados();

	@Query(value = "SELECT * FROM retirada r \n" + "inner join laticinio l on(r.retirada_laticinio = l.id) \n"
			+ "where (r.confirmacao or r.excluido) and (l.nome like concat('%', :nome, '%') \n"
			+ "|| l.nome_fantasia like concat('%', :nome, '%')) ORDER BY data_now DESC;", nativeQuery = true)
	public List<Retirada> buscaLaticinio(@Param("nome") String nome);

	@Query(value = "SELECT * FROM retirada r where (r.retirada_tanque like concat('%', :id, '%'))\n"
			+ "and r.excluido = 0 and r.confirmacao = 0", nativeQuery = true)
	public List<Retirada> buscaRetiradasPendentesPorTanque(@Param("id") Long id);
	
	@Query(value = "SELECT * FROM retirada r \n" + "inner join tanque t on(r.retirada_tanque = t.id)\n"
			+ "inner join responsavel rs on (t.responsavel_id = rs.id)\n"
			+ "where (rs.id like concat('%', :id, '%')) and (r.excluido or r.confirmacao) ORDER BY data_now DESC;", nativeQuery = true)
	public List<Retirada> buscaRetiradasPorTanqueResponsavel(@Param("id") Long id);
}
