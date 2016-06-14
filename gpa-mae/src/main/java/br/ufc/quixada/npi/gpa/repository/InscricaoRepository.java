package br.ufc.quixada.npi.gpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.npi.gpa.model.Inscricao;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Integer> {
	
	int countBySelecao_Id(Integer selecaoId);

}
