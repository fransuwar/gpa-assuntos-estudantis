package br.ufc.quixada.npi.gpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import br.ufc.quixada.npi.gpa.model.Inscricao;

public interface InscricaoRepository extends JpaRepository<Inscricao, Integer> {
	
	int countBySelecao_Id(Integer selecaoId);
	
	public abstract Inscricao findById(Integer idInscricao);
	
	@Query("select i from Inscricao i where i.selecao.id = :idSelecao")
	public abstract List<Inscricao> findInscricoesBySelecao(@Param("idSelecao") Integer idSelecao);
	
	@Query("select i from Inscricao i where i.selecao.id = :idSelecao and i.aluno.id =:idAluno")
	public abstract List<Inscricao> findInscricoesBySelecaoAndByAluno(@Param("idSelecao") Integer idSelecao, @Param("idAluno") Integer idAluno);
	
	@Query("SELECT i from Inscricao i where i.selecao.id =:idSelecao and i.aluno.id =:idAluno")
	public abstract Inscricao findInscricaoBySelecaoAndByAluno(@Param("idSelecao") Integer idSelecao, @Param("idAluno")Integer idAluno);
	
	@Query("select i from Inscricao as i where i.selecao.id =:idSelecao and i.classificado = 'true'")
	public abstract List<Inscricao> findClassificadosBySelecao(@Param("idSelecao") Integer idSelecao);
	
	@Query("select i from Inscricao as i where i.selecao.id =:idSelecao and i.classificado = 'false'")
	public abstract List<Inscricao> findClassificaveisBySelecao(@Param("idSelecao") Integer idSelecao);
	
	@Query("select i from Inscricao as i where i.selecao.id =:idSelecao and i.resultado = 'INDEFERIDO'")
	public abstract List<Inscricao> findIndeferidosBySelecao(@Param("idSelecao") Integer idSelecao);
	
}
