package br.ufc.quixada.npi.gpa.controller;

import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_SELECOES_ABERTAS;
import static br.ufc.quixada.npi.gpa.utils.Constants.RESULTADO;
import static br.ufc.quixada.npi.gpa.utils.Constants.SUCESSO;


import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.ufc.quixada.npi.gpa.excecoes.FalhaCarregarImagemException;
import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.service.InscricaoService;
import br.ufc.quixada.npi.gpa.service.SelecaoService;
import br.ufc.quixada.npi.gpa.utils.Constants;
import br.ufc.quixada.npi.model.Email;
import br.ufc.quixada.npi.service.EmailService;


@Controller
@RequestMapping("inscricao")
@SessionAttributes({ Constants.USUARIO_ID, Constants.USUARIO_LOGADO})
public class InscricaoController {
	
	@Inject
	private InscricaoService inscricaoService;
	
	@Inject
	private EmailService emailService;
	
	@Inject
	private SelecaoService selecaoService;
	
	private void enviarImagemPadraoEmCasoDeErro(HttpServletResponse response){
		try {
			response.setContentType("text/html");
			response.sendRedirect(Constants.CAMINHO_IMAGEM_ALUNO_SEM_FOTO);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("detalhes/fotoAluno/{idInscricao}")
	public void pegarFotoAluno(@PathVariable("idInscricao") Integer idInscricao, HttpServletResponse response){
		Inscricao inscricao = this.inscricaoService.getInscricaoPorId(idInscricao);
		
		try {
			
			if(inscricao.getQuestionarioAuxilioMoradia() == null || inscricao.getQuestionarioAuxilioMoradia().getFoto() == null){
				throw new FalhaCarregarImagemException();
			}
			
			response.setContentType("image/jpg");
			java.io.OutputStream out = response.getOutputStream();
			out.write(inscricao.getQuestionarioAuxilioMoradia().getFoto());
			out.flush();
		}
		catch(IOException e){
			enviarImagemPadraoEmCasoDeErro(response);
		}
		catch (FalhaCarregarImagemException e) {
			enviarImagemPadraoEmCasoDeErro(response);
		}
	}
	
	@RequestMapping(value = {"consolidar/{idInscricao}"}, method = RequestMethod.GET)
	public String consolidarInscricao(@PathVariable("idInscricao") Integer idInscricao,Model model){
		Inscricao inscricao = inscricaoService.getInscricaoPorId(idInscricao);
		List<Selecao> selecoes = selecaoService.getSelecoes();
		
		inscricao.setConsolidacao(true);
		inscricaoService.update(inscricao);	
		model.addAttribute("selecoes", selecoes);
		
		
		Runnable enviarEmail = new Runnable() {
			@Override
			public void run() {		
				Email email = new Email();
				String from = "naoresponda@gpaassuntosestudantis.com";
				String to = inscricao.getAluno().getPessoa().getEmail();
				System.out.println( inscricao.getAluno().getPessoa().getEmail());

				String body = "Caros amigos, a complexidade dos estudos efetuados desafia a capacidade de equalização de alternativas às soluções ortodoxas.";								
				email.setFrom(from);
				email.setSubject("Assunto");
				email.setText(body);
				email.setTo(to);

				try {

					emailService.sendEmail(email);

				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		};

		Thread threadEnviarEmail = new Thread(enviarEmail);
		threadEnviarEmail.run();

		return PAGINA_SELECOES_ABERTAS;
	}
	
	@RequestMapping(value = "consolidar", method = RequestMethod.GET,  produces=  MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Model consolidarViaAjax(@RequestParam("idInscricao") Integer idInscricao, @RequestParam("consolidacao") boolean consolidacao,Model model){
		inscricaoService.consolidar(idInscricao, consolidacao);
		model.addAttribute(RESULTADO,SUCESSO);
		return model;
		
	}
	
	@RequestMapping(value = "consolidarTodos", method = RequestMethod.GET,  produces=  MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Model consolidarTodos(@RequestParam("idSelecao") Integer idSelecao,@RequestParam("consolidacao") boolean consolidacao,Model model){
		inscricaoService.consolidacaoDeTodos(idSelecao, consolidacao);
		model.addAttribute(RESULTADO,SUCESSO);
		return model;
	}
}
