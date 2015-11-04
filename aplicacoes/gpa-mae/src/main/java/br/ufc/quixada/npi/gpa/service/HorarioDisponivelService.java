package br.ufc.quixada.npi.gpa.service;

import java.util.List;

import br.ufc.quixada.npi.gpa.model.HorarioDisponivel;
import br.ufc.quixada.npi.service.GenericService;

public interface HorarioDisponivelService extends GenericService<HorarioDisponivel> {
	
	public abstract List<HorarioDisponivel> getHorariosDisponiveisByQuest(Integer idQuest);

}
