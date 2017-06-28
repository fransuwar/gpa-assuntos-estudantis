package br.ufc.npi.auxilio.repository;

import br.ufc.npi.auxilio.enums.Resultado;
import br.ufc.npi.auxilio.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufc.npi.auxilio.model.Inscricao;
import br.ufc.npi.auxilio.model.Selecao;

import java.util.List;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Integer> {

	public Inscricao findInscricaoBySelecaoAndAluno(Selecao selecao, Aluno aluno);

	public List<Inscricao> findInscricaoByAluno(Aluno aluno);
	
	public List<Inscricao> findInscricaoBySelecaoAndEntrevistaAgendadaAndAnaliseDocumentacao_Resultado(Selecao selecao, Integer entrevistaAgendada, Resultado resultado);
	 	
	public List<Inscricao> findInscricaoBySelecaoOrderByPosicaoRankingAsc(Selecao selecao);
	
	public List<Inscricao> findAllBySelecao(Selecao selecao);

	public Inscricao findInscricaoById(Integer idInscricao); 
	
	@Query("select i from Inscricao i where i.resultado = 'INDEFERIDO' and i.selecao = :selecao")
	public List<Inscricao> getIndeferidos(@Param("selecao") Selecao selecao);
	
	@Query("select i from Inscricao i where i.selecionado = '1' and i.selecao = :selecao")
	public List<Inscricao> getSelecionados(@Param("selecao") Selecao selecao);

}
