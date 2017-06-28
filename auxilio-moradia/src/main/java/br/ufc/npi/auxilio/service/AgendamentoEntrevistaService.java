package br.ufc.npi.auxilio.service;

import java.util.List;

import br.ufc.npi.auxilio.excecao.AuxilioMoradiaException;
import br.ufc.npi.auxilio.model.AgendamentoEntrevista;
import br.ufc.npi.auxilio.model.Inscricao;
import br.ufc.npi.auxilio.model.Selecao;

public interface AgendamentoEntrevistaService {
	
	Boolean adicionarHorarioAgendamentoEntrevista(AgendamentoEntrevista agendamento)
			throws AuxilioMoradiaException;

	boolean alocarAgendamentoEntrevista(AgendamentoEntrevista agendamento, Inscricao inscricao) throws AuxilioMoradiaException;

	void removerInscricaoAgendamento(Inscricao inscricao, AgendamentoEntrevista agendamento) throws AuxilioMoradiaException;

	void excluirHorarioAgendamentoEntrevista(AgendamentoEntrevista agendamento) throws AuxilioMoradiaException;

	void editar(AgendamentoEntrevista agendamento);
	
	List<AgendamentoEntrevista> findAllDatas(Selecao selecao);

	List<AgendamentoEntrevista> findAll(Selecao selecao); 
	
	List<AgendamentoEntrevista> findBySelecao(Selecao selecao);
}
