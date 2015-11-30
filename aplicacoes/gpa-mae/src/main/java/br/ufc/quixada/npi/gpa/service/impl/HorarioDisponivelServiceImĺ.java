package br.ufc.quixada.npi.gpa.service.impl;

import java.util.List;

import javax.inject.Named;

import br.ufc.quixada.npi.gpa.model.HorarioDisponivel;
import br.ufc.quixada.npi.gpa.service.HorarioDisponivelService;
import br.ufc.quixada.npi.service.impl.GenericServiceImpl;
import br.ufc.quixada.npi.util.SimpleMap;

@Named
public class HorarioDisponivelServiceImĺ extends GenericServiceImpl<HorarioDisponivel>
		implements HorarioDisponivelService {

	@SuppressWarnings("unchecked")
	@Override
	public List<HorarioDisponivel> getHorariosDisponiveisByQuest(Integer idQuest) {
		return find("HorarioDisponivel.findHorarioDisponivelByIdQuest",
				new SimpleMap<String, Object>("id", idQuest));
	}

}
