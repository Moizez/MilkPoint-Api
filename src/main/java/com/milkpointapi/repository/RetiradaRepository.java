package com.milkpointapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.milkpointapi.model.Retirada;

@Repository
public interface RetiradaRepository extends JpaRepository<Retirada, Long> {
	@Query(value="select * from retirada r where r.excluido = 0 and r.confirmacao = 0 ORDER BY data_now ASC;", nativeQuery=true)
	public List<Retirada> buscaPendentes();
	
	@Query(value="select * from retirada r where r.excluido = 1 or r.confirmacao = 1 ORDER BY data_now DESC", nativeQuery=true)
	public List<Retirada> buscaResolvidos();	
	
	@Query(value="select * from retirada r where r.confirmacao = 1 ORDER BY data_now ASC", nativeQuery=true)
	public List<Retirada> buscaConfirmados();
	
	@Query(value="select * from retirada r where r.excluido = 1 ORDER BY data_now ASC", nativeQuery=true)
	public List<Retirada> buscaExcluidos();
}
