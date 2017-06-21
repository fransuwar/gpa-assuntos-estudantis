package br.ufc.npi.auxilio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.auxilio.excecao.AuxilioMoradiaException;
import br.ufc.npi.auxilio.model.AgendamentoEntrevista;
import br.ufc.npi.auxilio.model.Inscricao;
import br.ufc.npi.auxilio.repository.AgendamentoEntrevistaRepository;
import br.ufc.npi.auxilio.service.AgendamentoEntrevistaService;
import br.ufc.npi.auxilio.service.EmailService;

@Service
public class AgendamentoEntrevistaServiceImpl implements AgendamentoEntrevistaService{

	@Autowired
	private AgendamentoEntrevistaRepository agendamentoEntrevistaRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Override
	public Boolean adicionarHorarioAgendamentoEntrevista(AgendamentoEntrevista agendamento)
			throws AuxilioMoradiaException {
		if(agendamento != null){
			agendamentoEntrevistaRepository.save(agendamento);
			return true;
		}
		return false;
	}

	@Override
	public List<AgendamentoEntrevista> findAll() {
		return agendamentoEntrevistaRepository.findAll();
	}

	@Override
	public boolean alocarAgendamentoEntrevista(AgendamentoEntrevista agendamento, Inscricao inscricao) {
		if (inscricao != null && agendamento != null) {
			Boolean b  = agendamento.alocaAgendamentoEntrevista(inscricao);
			agendamentoEntrevistaRepository.save(agendamento);
			emailService.enviarEmailEntrevistaAgendada(agendamento, inscricao);
			return b;
		}
		return false;
	}

	@Override
	public void removerInscricaoAgendamento(Inscricao inscricao, AgendamentoEntrevista agendamento) {
		if (inscricao != null && agendamento != null) {
			
			agendamento.removeInscricaoAgendamento(inscricao);
			agendamentoEntrevistaRepository.save(agendamento);
		}
	}

}
