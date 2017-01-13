package br.ufc.npi.auxilio.service.impl;

import javax.inject.Named;

import br.ufc.npi.auxilio.excecao.AuxilioMoradiaException;
import br.ufc.npi.auxilio.model.questionario.Identificacao;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufc.npi.auxilio.model.Aluno;
import br.ufc.npi.auxilio.model.Inscricao;
import br.ufc.npi.auxilio.model.Pessoa;
import br.ufc.npi.auxilio.model.Selecao;
import br.ufc.npi.auxilio.repository.InscricaoRepository;
import br.ufc.npi.auxilio.service.InscricaoService;

import java.util.Date;

import static br.ufc.npi.auxilio.utils.ErrorMessageConstants.MENSAGEM_ERRO_INSCRICAO_EXISTENTE;
import static br.ufc.npi.auxilio.utils.ErrorMessageConstants.MENSAGEM_ERRO_INSCRICAO_FORA_DO_PRAZO;
import static br.ufc.npi.auxilio.utils.ErrorMessageConstants.MENSAGEM_ERRO_REALIZAR_INSCRICAO;

@Named
public class InscricaoServiceImpl implements InscricaoService {
	
	@Autowired
	private InscricaoRepository inscricaoRepository;
	
	@Override
	public boolean isInscrito(Aluno aluno, Selecao selecao) {
		return inscricaoRepository.findInscricaoBySelecaoAndAluno(selecao, aluno) != null;
	}
	
	@Override
	public void salvar(Selecao selecao, Aluno aluno, Identificacao identificacao) throws AuxilioMoradiaException{
		if (aluno != null && selecao != null) {
			// Verifica se a seleção está com período de inscrição aberto
			if (!selecao.isInscricaoAberta()) {
				throw new AuxilioMoradiaException(MENSAGEM_ERRO_INSCRICAO_FORA_DO_PRAZO);
			}
			// Verifica se o aluno já possui inscrição nessa seleção
			if(isInscrito(aluno, selecao)) {
				throw new AuxilioMoradiaException(MENSAGEM_ERRO_INSCRICAO_EXISTENTE);
			}
			// Cria uma nova inscrição
			Inscricao inscricao = new Inscricao();
			aluno.setIdentificacao(identificacao);
			inscricao.setSelecao(selecao);
			inscricao.setAluno(aluno);
			inscricao.setData(new Date());
			inscricaoRepository.save(inscricao);
		} else {
			throw new AuxilioMoradiaException(MENSAGEM_ERRO_REALIZAR_INSCRICAO);
		}
	}

	@Override
	public Inscricao get(Aluno aluno, Selecao selecao) {
		return inscricaoRepository.findInscricaoBySelecaoAndAluno(selecao, aluno);
	}

	@Override
	public Inscricao salvar(Inscricao inscricao) {
		return inscricaoRepository.save(inscricao);
	}
}