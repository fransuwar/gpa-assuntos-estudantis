package br.ufc.npi.auxilio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.auxilio.excecao.AuxilioMoradiaException;
import br.ufc.npi.auxilio.model.AgendamentoEntrevista;
import br.ufc.npi.auxilio.model.Inscricao;
import br.ufc.npi.auxilio.model.Selecao;
import br.ufc.npi.auxilio.repository.AgendamentoEntrevistaRepository;
import br.ufc.npi.auxilio.service.AgendamentoEntrevistaService;
import br.ufc.npi.auxilio.service.EmailService;
import br.ufc.npi.auxilio.service.InscricaoService;

@Service
public class AgendamentoEntrevistaServiceImpl implements AgendamentoEntrevistaService{

	@Autowired
	private AgendamentoEntrevistaRepository agendamentoEntrevistaRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private InscricaoService inscricaoService;
	
	@Override
	public Boolean adicionarHorarioAgendamentoEntrevista(AgendamentoEntrevista agendamento)
			throws AuxilioMoradiaException {
			List<AgendamentoEntrevista> agendamentos = agendamentoEntrevistaRepository.findAll();
			if(agendamentos.size()!=0){
				for(AgendamentoEntrevista ea : agendamentos ){
					if(ea.getData().equals(agendamento.getData()) && ea.getHorario().equals(agendamento.getHorario()) && ea.getTurno().equals(agendamento.getTurno())){
						return false;
					}
				}
			}
			agendamentoEntrevistaRepository.save(agendamento);
			return true;
	}

	@Override
	public List<AgendamentoEntrevista> findAll(Selecao selecao) {
		return agendamentoEntrevistaRepository.findAllByOrderByData();
	}

	@Override
	public boolean alocarAgendamentoEntrevista(AgendamentoEntrevista agendamento, Inscricao inscricao) throws AuxilioMoradiaException {
		if(inscricao != null && agendamento != null) {
			Boolean b  = agendamento.alocaAgendamentoEntrevista(inscricao);
			inscricao.setEntrevistaAgendada(1);
			inscricaoService.atualizar(inscricao);
			agendamentoEntrevistaRepository.save(agendamento);
			emailService.enviarEmailEntrevistaAgendada(agendamento, inscricao);
			return b;
		}
		return false;
	}

	@Override
	public void removerInscricaoAgendamento(Inscricao inscricao, AgendamentoEntrevista agendamento) throws AuxilioMoradiaException {
		if (inscricao != null && agendamento != null) {
			inscricao.setEntrevistaAgendada(0);
			inscricaoService.atualizar(inscricao);
			agendamento.removeInscricaoAgendamento(inscricao);
			agendamentoEntrevistaRepository.save(agendamento);
		}
	}

	@Override
	public void excluirHorarioAgendamentoEntrevista(AgendamentoEntrevista agendamento) throws AuxilioMoradiaException {
		if (agendamento.getInscricoes() != null) {
			for(int i = 0; i < agendamento.getInscricoes().size(); i++)
			{
				agendamento.getInscricoes().get(i).setEntrevistaAgendada(0);
			}
			agendamentoEntrevistaRepository.delete(agendamento);
		}
		else{
			throw new AuxilioMoradiaException("O agendamento possui inscricões associadas");
		}
	}
	
	public AgendamentoEntrevista getById(Integer id){
		return agendamentoEntrevistaRepository.findById(id);
	}

	@Override
	public void editar(AgendamentoEntrevista agendamento) {
		if(agendamento != null){
			List<Inscricao> inscricoes = this.getById(agendamento.getId()).getInscricoes();
			agendamento.setInscricoes(inscricoes);
			agendamentoEntrevistaRepository.save(agendamento);
		}		
	}

	@Override
	public List<AgendamentoEntrevista> findAllDatas(Selecao selecao) {
		List<AgendamentoEntrevista> agendamentos = agendamentoEntrevistaRepository.findDistinctDataBy(selecao.getId());
		return agendamentos;
	}

}
