package br.ufc.npi.auxilio.service.impl;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

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
		            helper.setText("Você foi selecionado para a comissão da seleção " + selecao.getTipo().getNome()+" "+ selecao.getAno() + ".");
		        } catch (MessagingException e) {
		            e.printStackTrace();
		        }
		        mailSender.send(mail);
		}
}
