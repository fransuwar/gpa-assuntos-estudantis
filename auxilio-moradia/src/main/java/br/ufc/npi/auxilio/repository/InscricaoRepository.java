package br.ufc.npi.auxilio.repository;

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
	
	@Query("SELECT a from Inscricao a where")
	public List<Inscricao> findAllInscricaoAptasParaEntrevista(@Param("idSelecao") Integer idSelecao);
	 	
}
