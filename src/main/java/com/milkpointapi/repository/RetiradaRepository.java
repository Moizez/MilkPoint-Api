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
}
