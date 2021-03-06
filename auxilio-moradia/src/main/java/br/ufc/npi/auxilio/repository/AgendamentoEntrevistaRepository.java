package br.ufc.npi.auxilio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufc.npi.auxilio.model.AgendamentoEntrevista;
import br.ufc.npi.auxilio.model.Selecao;

@Repository
public interface AgendamentoEntrevistaRepository extends JpaRepository <AgendamentoEntrevista, Integer>{
	
	
	public abstract List<AgendamentoEntrevista> findAllByOrderByData();
	
	public abstract List<AgendamentoEntrevista> findAllBySelecao(Selecao selecao);
	
	public abstract AgendamentoEntrevista findById(Integer id);
	
	@Query("select DISTINCT(p.data) FROM AgendamentoEntrevista p inner join p.selecao s where s.id = :idSelecao")
	public abstract List<AgendamentoEntrevista> findDistinctDataBy(@Param("idSelecao") Integer selecaoId);
	
	@Query("SELECT a FROM AgendamentoEntrevista a WHERE a.selecao = :selecao")
	public abstract List<AgendamentoEntrevista> findBySelecao(@Param("selecao") Selecao selecao);
	
}
