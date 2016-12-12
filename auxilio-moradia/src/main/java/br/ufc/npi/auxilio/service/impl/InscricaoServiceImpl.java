package br.ufc.npi.auxilio.service.impl;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufc.npi.auxilio.excecao.AuxilioMoradiaException;
import br.ufc.npi.auxilio.model.Aluno;
import br.ufc.npi.auxilio.model.Pessoa;
import br.ufc.npi.auxilio.model.Selecao;
import br.ufc.npi.auxilio.repository.InscricaoRepository;
import br.ufc.npi.auxilio.service.InscricaoService;
import br.ufc.npi.auxilio.utils.ErrorMessageConstants;

@Named
public class InscricaoServiceImpl implements InscricaoService {
	
	@Autowired
	private InscricaoRepository inscricaoRepository;
	
	@Override
	public boolean estaInscrito(Pessoa pessoa, Selecao selecao) {
		return inscricaoRepository.findInscricaoBySelecaoAndAlunoPessoa(selecao, pessoa) != null;
	}
	
	@Override
	public void salvar(Selecao selecao, Aluno aluno) throws AuxilioMoradiaException {
		if (inscricaoRepository.findInscricaoBySelecaoAndAlunoPessoa(selecao, aluno.getPessoa()) != null){
			inscricaoRepository.save(selecao, aluno);
		}else{
			throw new AuxilioMoradiaException(ErrorMessageConstants.ERRO_INSCRICAO_DUPLICADA);
		}
	}
}