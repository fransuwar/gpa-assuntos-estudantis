package br.ufc.npi.auxilio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.npi.auxilio.model.VisitaDomiciliar;

@Repository
public interface VisitaDomiciliarRepository extends JpaRepository<VisitaDomiciliar, Integer>{
	
	public abstract VisitaDomiciliar findById(Integer idVisita);

}
