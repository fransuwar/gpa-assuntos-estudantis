package br.ufc.npi.auxilio.service.impl;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufc.npi.auxilio.model.DadosBancarios;
import br.ufc.npi.auxilio.repository.DadosBancariosRepository;
import br.ufc.npi.auxilio.service.DadosBancariosService;

@Named
public class DadosBancariosServiceImpl implements DadosBancariosService{

	@Autowired
	DadosBancariosRepository dadosBancariosRepository;
	
	@Override
	public DadosBancarios salvar(DadosBancarios dadosBancarios) {
		return dadosBancariosRepository.save(dadosBancarios);
	}

}
