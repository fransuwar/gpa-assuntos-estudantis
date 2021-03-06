package br.ufc.quixada.npi.gpa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.quixada.npi.gpa.model.TipoDocumento;
import br.ufc.quixada.npi.gpa.repository.TipoDocumentoRepository;
import br.ufc.quixada.npi.gpa.service.DocumentacaoService;

@Service
public class DocumentacaoServiceImpl implements DocumentacaoService {

	@Autowired
	private TipoDocumentoRepository tipoDocumentoRepository;
	
	@Override
	public List<TipoDocumento> getAllTipoDocumento() {
		return tipoDocumentoRepository.findAll();
	}

	@Override
	public TipoDocumento getTipoDocumentoById(Integer id) {
		return tipoDocumentoRepository.findOne(id);
	}

	@Override
	public void excluirTipoDocumento(Integer id) {
		tipoDocumentoRepository.delete(id);
	}

	@Override
	public void salvar(TipoDocumento tipoDocumento) {
		tipoDocumentoRepository.save(tipoDocumento);
	}

}
