package br.ufc.quixada.npi.gpa.service.impl;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.gpa.model.VisitaDomiciliar;
import br.ufc.quixada.npi.gpa.service.RelatorioVisitaDomiciliarService;
import br.ufc.quixada.npi.service.impl.GenericServiceImpl;
import br.ufc.quixada.npi.util.SimpleMap;

@Named
public class RelatorioVisitaDomiciliarServiceImpl extends GenericServiceImpl<VisitaDomiciliar> 
implements RelatorioVisitaDomiciliarService{

	@Override
	@Transactional(readOnly = true)
	public VisitaDomiciliar getRelatorioVisitaById(Integer id) {
		return (VisitaDomiciliar) findFirst("RelatorioVisita.findRelatorioVisitaById", new SimpleMap<String, Object>("alunoId",id));
	}
	
	

}
