package br.ufc.npi.auxilio.service.impl;


import java.time.format.DateTimeFormatter;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.ufc.npi.auxilio.model.AgendamentoEntrevista;
import br.ufc.npi.auxilio.model.Inscricao;
import br.ufc.npi.auxilio.model.Selecao;
import br.ufc.npi.auxilio.model.Servidor;
import br.ufc.npi.auxilio.service.EmailService;



@Service
public class EmailServiceImpl implements EmailService{
	
	@Autowired
		private JavaMailSender mailSender;
	
		@Override
		public void enviarEmailComissao(Servidor servidor, Selecao selecao) {
			
			 MimeMessage mail = mailSender.createMimeMessage();
		        try {
		            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
		            helper.setTo(servidor.getPessoa().getEmail());
		            helper.setReplyTo("someone@localhost");
		            helper.setFrom("auxilioMoradia@gmail.com");
		            helper.setSubject("Comissão de Entrevista para bolsa");
		            helper.setText("Você foi selecionado, como membro da comissão, para participar da etapa de entrevista da seleção " + selecao.getTipo().getNome()+" "+ selecao.getAno() + ".");
		        } catch (MessagingException e) {
		            e.printStackTrace(); 
		        }
		        mailSender.send(mail);
		}

		@Override
		public void enviarEmailEntrevistaAgendada(AgendamentoEntrevista agendamento, Inscricao inscricao) {
			MimeMessage mail = mailSender.createMimeMessage();
	        try {
	            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
	            DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	            String data = agendamento.getData().format(formatters);
	            
	            helper.setTo(inscricao.getAluno().getPessoa().getEmail());
	            helper.setReplyTo("someone@localhost");
	            helper.setFrom("Seleção Acadêmica <auxilioMoradia@gmail.com>");
	            helper.setSubject("Agendamento de Entrevista para bolsa");
	            helper.setText("Caro candidato,\nSua entrevista para a seleção "+agendamento.getSelecao().getTipo().getNome()+" já têm data marcada, conforme os dados abaixo:"
	            		+ "\nData: "+ data + 
	            		".\nHorário: "+ agendamento.getHorario().getNome()
	            		+ ".\nTurno: " + agendamento.getTurno().getNome()+".");

	        } catch (MessagingException e) {
		            e.printStackTrace(); 
		        }
		        mailSender.send(mail);
		}
}
