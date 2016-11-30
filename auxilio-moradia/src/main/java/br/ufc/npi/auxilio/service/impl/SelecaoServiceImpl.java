package br.ufc.npi.auxilio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.auxilio.model.Selecao;
import br.ufc.npi.auxilio.repository.SelecaoRepository;
import br.ufc.npi.auxilio.service.SelecaoService;

@Service
public class SelecaoServiceImpl implements SelecaoService {

	@Autowired
	private SelecaoRepository selecaoRepository;
	
	@Override
	public List<Selecao> getAll() {
		return selecaoRepository.findAll();
	}

	@Override
	public void salvar(Selecao selecao) {
		selecaoRepository.save(selecao);
	}

}
