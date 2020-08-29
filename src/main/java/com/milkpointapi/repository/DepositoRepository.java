package com.milkpointapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.milkpointapi.model.Deposito;

@Repository
public interface DepositoRepository extends JpaRepository<Deposito, Long> {
	
	@Query(value="select * from deposito d where d.excluido = 0 and d.confirmacao = 0 ORDER BY data_now ASC", nativeQuery=true)
	public List<Deposito> buscaPendentes();
}
