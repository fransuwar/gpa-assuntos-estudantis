package br.ufc.quixada.npi.gpa.service.impl;





import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.gpa.model.Entrevista;
import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.service.InscricaoService;
import br.ufc.quixada.npi.service.impl.GenericServiceImpl;
import br.ufc.quixada.npi.util.SimpleMap;

@Named
public class InscricaoServiceImpl extends GenericServiceImpl<Inscricao> implements InscricaoService{
	
	private GenericServiceImpl<Entrevista>  entrevistaService;
	
	@Override
	@Transactional(readOnly = true)
	public Inscricao getInscricaoId(Integer id) {
		return (Inscricao) findFirst("Incricao.findIncricaoId", new SimpleMap<String, Object>("id", id));
	}

	@Override
	public void saveEntrevista(Entrevista entrevista) {
			entrevistaService.save(entrevista);		
	}


	
}
