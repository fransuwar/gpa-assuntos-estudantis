package br.ufc.quixada.npi.gpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.quixada.npi.gpa.model.Entrevista;

public interface EntrevistaRepository extends JpaRepository <Entrevista, Integer>{
	
	public abstract Entrevista findById(Integer idEntrevista);

}
