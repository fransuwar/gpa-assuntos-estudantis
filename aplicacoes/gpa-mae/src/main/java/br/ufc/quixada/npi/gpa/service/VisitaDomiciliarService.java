package br.ufc.quixada.npi.gpa.service;

import br.ufc.quixada.npi.gpa.model.VisitaDomiciliar;

public interface VisitaDomiciliarService extends GenericService<VisitaDomiciliar>{
	public abstract VisitaDomiciliar getRelatorioVisitaById(Integer id);


}
