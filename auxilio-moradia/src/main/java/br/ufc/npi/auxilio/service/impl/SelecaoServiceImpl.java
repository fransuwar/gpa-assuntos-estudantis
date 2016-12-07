package br.ufc.npi.auxilio.service.impl;

import java.util.List;

import br.ufc.npi.auxilio.excecao.AuxilioMoradiaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.auxilio.model.Selecao;
import br.ufc.npi.auxilio.model.Servidor;
import br.ufc.npi.auxilio.repository.SelecaoRepository;
import br.ufc.npi.auxilio.repository.ServidorRepository;
import br.ufc.npi.auxilio.service.SelecaoService;

import static br.ufc.npi.auxilio.utils.ExceptionConstants.CAMPOS_OBRIGATORIOS;
import static br.ufc.npi.auxilio.utils.ExceptionConstants.PERIODO_INSCRICAO_EXCEPTION;
import static br.ufc.npi.auxilio.utils.ExceptionConstants.SELECAO_COM_INSCRICAO_EXCEPTION;

@Service
public class SelecaoServiceImpl implements SelecaoService {
	
	@Autowired
	private SelecaoRepository selecaoRepository;
	
	@Autowired
	private ServidorRepository servidorRepository;
	
	@Override
	public void cadastrar(Selecao selecao) throws AuxilioMoradiaException {
		// Verifica se todos os campos obrigatórios foram preenchidos
		if (selecao.getAno() == null || selecao.getDataInicio() == null || selecao.getDataTermino() == null) {
			throw new AuxilioMoradiaException(CAMPOS_OBRIGATORIOS);
		}
		// Verifica se a data de término é posterior à data de início
		if (selecao.getDataTermino().before(selecao.getDataInicio())) {
			throw new AuxilioMoradiaException(PERIODO_INSCRICAO_EXCEPTION);
		}
		// Inclui o responsável na comissão da seleção
		selecao.addMembroComissao(selecao.getResponsavel());

		selecaoRepository.save(selecao);
	}

	@Override
	public List<Selecao> getAll() {
		return selecaoRepository.findAll();
	}

	@Override
	public void excluir(Selecao selecao) throws AuxilioMoradiaException {
		if (selecao.hasInscricoes()) {
			throw new AuxilioMoradiaException(SELECAO_COM_INSCRICAO_EXCEPTION);
		}
		selecaoRepository.delete(selecao);
	}
	
}
