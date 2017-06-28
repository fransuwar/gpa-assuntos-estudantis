package br.ufc.npi.auxilio.service;

import br.ufc.npi.auxilio.model.AgendamentoEntrevista;
import br.ufc.npi.auxilio.model.Inscricao;
import br.ufc.npi.auxilio.model.Selecao;
import br.ufc.npi.auxilio.model.Servidor;

public interface EmailService {
	
	void enviarEmailComissao(Servidor servidor, Selecao selecao);

	void enviarEmailEntrevistaAgendada(AgendamentoEntrevista agendamento, Inscricao inscricao);
}
