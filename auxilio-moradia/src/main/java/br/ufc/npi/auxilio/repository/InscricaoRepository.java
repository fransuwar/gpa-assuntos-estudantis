package br.ufc.npi.auxilio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.npi.auxilio.model.Aluno;
import br.ufc.npi.auxilio.model.Inscricao;
import br.ufc.npi.auxilio.model.Pessoa;
import br.ufc.npi.auxilio.model.Selecao;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Integer> {

	public Inscricao findInscricaoBySelecaoAndAlunoPessoa(Selecao selecao, Pessoa pessoa);
	
	public void save(Selecao selecao, Aluno aluno);
	 	
}
