package br.ufc.quixada.npi.gpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.model.Selecao;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Integer> {
	
	int countBySelecao_Id(Integer selecaoId);
	
	List<Selecao> getBySelecao(Integer id);

}
