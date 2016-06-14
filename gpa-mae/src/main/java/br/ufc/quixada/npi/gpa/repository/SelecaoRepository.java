package br.ufc.quixada.npi.gpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.npi.gpa.model.Selecao;

@Repository
public interface SelecaoRepository extends JpaRepository<Selecao, Integer> {
	

}
