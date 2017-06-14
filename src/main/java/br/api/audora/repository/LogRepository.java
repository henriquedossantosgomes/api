package br.api.audora.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.api.audora.domain.Log;

@Repository("logRepository")
public interface LogRepository extends CrudRepository<Log, Long>, JpaSpecificationExecutor<Log>{

	Iterable<Log> findByProduto(String produto);

	Iterable<Log> findByCliente(String cliente);

	Iterable<Log> findByCategoria(String categoria);

	@Query("select l from Log l where l.dataHora between :dataInicio and :dataFim")
	Iterable<Log> findByIntervaloDataHora(@Param("dataInicio") Date dataInicio, @Param("dataFim")Date dataFim);

}

