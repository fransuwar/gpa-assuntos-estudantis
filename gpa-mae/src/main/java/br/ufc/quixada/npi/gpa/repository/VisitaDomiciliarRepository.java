package br.ufc.quixada.npi.gpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.quixada.npi.gpa.model.VisitaDomiciliar;

public interface VisitaDomiciliarRepository extends JpaRepository<VisitaDomiciliar, Integer>{
	
	public abstract VisitaDomiciliar findById(Integer idVisita);

}
