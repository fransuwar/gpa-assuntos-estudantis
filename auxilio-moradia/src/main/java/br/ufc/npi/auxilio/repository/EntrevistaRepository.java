package br.ufc.npi.auxilio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.npi.auxilio.model.Entrevista;

@Repository
public interface EntrevistaRepository extends JpaRepository <Entrevista, Integer>{
	
	public abstract Entrevista findById(Integer idEntrevista);

}
