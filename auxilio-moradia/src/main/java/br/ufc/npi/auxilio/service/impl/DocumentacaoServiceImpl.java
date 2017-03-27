package br.ufc.npi.auxilio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.auxilio.model.TipoDocumento;
import br.ufc.npi.auxilio.repository.TipoDocumentoRepository;
import br.ufc.npi.auxilio.service.DocumentacaoService;

@Service
public class DocumentacaoServiceImpl implements DocumentacaoService {

	@Autowired
	private TipoDocumentoRepository tipoDocumentoRepository;
	
	@Override
	public List<TipoDocumento> getAllTipoDocumento() {
		return tipoDocumentoRepository.findAll();
	}

	@Override
	public void salvar(TipoDocumento tipoDocumento) {
		tipoDocumentoRepository.save(tipoDocumento);
	}

}
