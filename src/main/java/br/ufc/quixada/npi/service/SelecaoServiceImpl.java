package br.ufc.quixada.npi.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import br.ufc.quixada.npi.model.Selecao;
import br.ufc.quixada.npi.repository.SelecaoRepository;
@Named
public class SelecaoServiceImpl extends GenericServiceImpl<Selecao> implements SelecaoService {

	@Inject
	private SelecaoRepository selecaoRepository;
	
	@Override
	public List<Selecao> getSelecao() {
		return selecaoRepository.find(Selecao.class);
	}
}
	
	
	
	

