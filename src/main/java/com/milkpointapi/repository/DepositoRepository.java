package com.milkpointapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.milkpointapi.model.Deposito;

@Repository
public interface DepositoRepository extends JpaRepository<Deposito, Long> {

	@Query(value = "select * from deposito d where d.excluido = 0 and d.confirmacao = 0 ORDER BY data_now ASC", nativeQuery = true)
	public List<Deposito> buscaPendentes();

	@Query(value = "select * from deposito d where d.excluido = 1 or d.confirmacao = 1 ORDER BY data_now DESC", nativeQuery = true)
	public List<Deposito> buscaResolvidos();

	@Query(value = "select * from deposito d where d.confirmacao = 1 ORDER BY data_now ASC", nativeQuery = true)
	public List<Deposito> buscaConfirmados();

	@Query(value = "select * from deposito d where d.excluido = 1 ORDER BY data_now ASC", nativeQuery = true)
	public List<Deposito> buscaExcluidos();

	@Query(value = "SELECT * FROM deposito d \n" + "inner join produtor p on(d.deposito_produtor = p.id)\n"
			+ "where (d.confirmacao or d.excluido) and (p.nome like concat('%', :nome, '%')) ORDER BY data_now DESC;", nativeQuery = true)
	public List<Deposito> buscaProdutor(@Param("nome") String nome);

}
