package br.ufc.npi.auxilio.service.impl;


import org.springframework.stereotype.Service;

import br.ufc.npi.auxilio.model.Selecao;
import br.ufc.npi.auxilio.model.Servidor;
import br.ufc.npi.auxilio.service.EmailService;



@Service
public class EmailServiceImpl implements EmailService{
	/*	@Autowired
		private Environment env;
	
		@Autowired
		private JavaMailSender mailSender;
	
		@Autowired
		private TemplateEngine templateEngine;
	
		private void enviarEmail(MimeMessage email) {
			this.mailSender.send(email);
		}
	*/
	
		@Override
		public void enviarEmailComissao(Servidor servidor, Selecao selecao) {
			// TODO Auto-generated method stub
			System.out.println("Enviar o email");
		}
}
