package br.ufc.quixada.npi.gpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.npi.gpa.model.Selecao;

@Repository
public interface SelecaoRepository extends JpaRepository<Selecao, Integer> {
	
	public abstract List<Selecao> findAll();
	
	public abstract Selecao findById(Integer idSelecao);
	
	@Query("select coalesce(MAX(sequencial), 0) + 1 from Selecao where ano = :ano")
	public abstract Integer getNextSequencial(@Param("ano") Integer ano);

}
