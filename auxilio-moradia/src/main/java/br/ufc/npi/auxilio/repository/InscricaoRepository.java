package br.ufc.npi.auxilio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.npi.auxilio.model.Inscricao;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Integer> {
	
	int countBySelecao_Id(Integer selecaoId);
	
	public abstract Inscricao findById(Integer idInscricao);
	
	List<Inscricao> findByAlunoPessoaCpf(String cpf);
	
	@Query("select i from Inscricao i where i.selecao.id = :idSelecao")
	public abstract List<Inscricao> findInscricoesBySelecao(@Param("idSelecao") Integer idSelecao);
	
	@Query("select i from Inscricao i where i.selecao.id = :idSelecao and i.aluno.id =:idAluno")
	public abstract List<Inscricao> findInscricoesBySelecaoAndByAluno(@Param("idSelecao") Integer idSelecao, @Param("idAluno") Integer idAluno);
	
	@Query("SELECT i from Inscricao i where i.selecao.id =:idSelecao and i.aluno.id =:idAluno")
	public abstract Inscricao findInscricaoBySelecaoAndByAluno(@Param("idSelecao") Integer idSelecao, @Param("idAluno")Integer idAluno);
	
	@Query("select i from Inscricao as i where i.selecao.id =:idSelecao and i.classificado = 'true'")
	public abstract List<Inscricao> findClassificadosBySelecao(@Param("idSelecao") Integer idSelecao);
	
	@Query("select i from Inscricao as i where i.selecao.id =:idSelecao and i.classificado = 'false' and i.resultado != 'INDEFERIDO'")
	public abstract List<Inscricao> findClassificaveisBySelecao(@Param("idSelecao") Integer idSelecao);
	
	@Query("select i from Inscricao as i where i.selecao.id =:idSelecao and i.resultado = 'INDEFERIDO'")
	public abstract List<Inscricao> findIndeferidosBySelecao(@Param("idSelecao") Integer idSelecao);
	
	@Transactional
	@Modifying
	@Query("update Inscricao set consolidacao = :consolidacao where id = :idInscricao")
	public abstract int consolidar(@Param("idInscricao") Integer idInscricao, @Param("consolidacao") boolean consolidacao);
	
	@Transactional
	@Modifying
	@Query("update Inscricao set consolidacao = :consolidacao where selecao.id = :idSelecao")
	public abstract int consolidarTodos(@Param("idSelecao")Integer idSelecao, @Param("consolidacao") boolean consolidacao);
	
	@Transactional
	@Modifying
	@Query("update Inscricao set classificado = :classificado where id = :idInscricao")
	public abstract int atualizarClassificacao(@Param("idInscricao")Integer idInscricao, @Param("classificado") boolean classificado);
}
