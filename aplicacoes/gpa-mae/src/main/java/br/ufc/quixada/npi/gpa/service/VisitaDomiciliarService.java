package br.ufc.quixada.npi.gpa.service;

import br.ufc.quixada.npi.gpa.model.VisitaDomiciliar;
import br.ufc.quixada.npi.service.GenericService;

public interface VisitaDomiciliarService extends GenericService<VisitaDomiciliar>{
	
	public abstract VisitaDomiciliar getRelatorioVisitaById(Integer id);


}
