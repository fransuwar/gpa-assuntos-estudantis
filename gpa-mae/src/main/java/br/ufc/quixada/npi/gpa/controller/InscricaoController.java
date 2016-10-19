package br.ufc.quixada.npi.gpa.controller;

import static br.ufc.quixada.npi.gpa.utils.Constants.ABA_SELECIONADA;
import static br.ufc.quixada.npi.gpa.utils.Constants.ENTREVISTA;
import static br.ufc.quixada.npi.gpa.utils.Constants.ERRO;
import static br.ufc.quixada.npi.gpa.utils.Constants.ESCONDER_BOTOES;
import static br.ufc.quixada.npi.gpa.utils.Constants.INFO;
import static br.ufc.quixada.npi.gpa.utils.Constants.INSCRICAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.INSCRICAO_TAB;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_FALTA_DE_PERMISSAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_SUCESSO_INSCRICAO_CONSOLIDADA;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_DETALHES_INICIACAO_ACADEMICA;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_DETALHES_INSCRICAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_ALUNO_LISTAR_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_DETALHES_INSCRICAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.RESULTADO;
import static br.ufc.quixada.npi.gpa.utils.Constants.SUCESSO;
import static br.ufc.quixada.npi.gpa.utils.Constants.USUARIO_ATIVO;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.npi.gpa.enums.Escolaridade;
import br.ufc.quixada.npi.gpa.enums.GrauParentesco;
import br.ufc.quixada.npi.gpa.excecoes.FalhaCarregarImagemException;
import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.model.Entrevista;
import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.model.PessoaFamilia;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.repository.AlunoRepository;
import br.ufc.quixada.npi.gpa.repository.InscricaoRepository;
import br.ufc.quixada.npi.gpa.repository.SelecaoRepository;
import br.ufc.quixada.npi.gpa.utils.Constants;

@Controller
@RequestMapping("inscricao")
public class InscricaoController {

	@Inject
	private InscricaoRepository inscricaoRepository;

	/*@Inject
	private EmailService emailService;*/

	@Inject
	private SelecaoRepository selecaoRepository;
	
	@Inject
	private AlunoRepository alunoRepository;

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
		Inscricao inscricao = this.inscricaoRepository.findById(idInscricao);

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

	@RequestMapping(value = { "detalhes/{idInscricao}" }, method = RequestMethod.GET)
	public String detalhesInscricao(@PathVariable("idInscricao") Integer idInscricao, Model model) {
		
		Inscricao inscricao = inscricaoRepository.findById(idInscricao);
		Selecao selecao = inscricao.getSelecao();
		Date date = new Date();
		model.addAttribute(INSCRICAO, inscricao);
		model.addAttribute(USUARIO_ATIVO, inscricao.getAluno().getPessoa());
		model.addAttribute("pessoaDaFamilia",new PessoaFamilia());

			model.addAttribute(INSCRICAO, inscricao);
			model.addAttribute(USUARIO_ATIVO, inscricao.getAluno().getPessoa());
			
			//Verificando se alguma aba específica foi setada no redirect
			String nomeAba = (String) model.asMap().getOrDefault(ABA_SELECIONADA, null);
			
			if(nomeAba == null){
				//Se nenhuma aba foi setada então a aba padrão é selecionada 
				nomeAba = INSCRICAO_TAB; 
			}

			model.addAttribute(ABA_SELECIONADA, nomeAba);
			
			String msgSucesso = (String) model.asMap().getOrDefault(INFO, null);
			
			if(msgSucesso != null){
				model.addAttribute(INFO, msgSucesso);
			}
			
			if(inscricao.getEntrevista()!=null) {
				model.addAttribute(ENTREVISTA, inscricao.getEntrevista());
			}
			else {
				model.addAttribute(ENTREVISTA, new Entrevista());
			    model.addAttribute("grauParentesco", GrauParentesco.values());
			    model.addAttribute("escolaridade",Escolaridade.values());
			}
		

		 if (inscricao.getQuestionarioAuxilioMoradia() != null) {

			if(date.before(selecao.getDataInicio()) || date.after(selecao.getDataTermino())){
				model.addAttribute(ESCONDER_BOTOES,true);
			} else{
				model.addAttribute(ESCONDER_BOTOES,false);			
			}

			//Recebendo a mensagem recebida do redirect
			String msgAddDocumentos = (String) model.asMap().getOrDefault(INFO, null);

			if(msgAddDocumentos != null){
				model.addAttribute(INFO,msgAddDocumentos);
			}

			return PAGINA_DETALHES_INSCRICAO;

		} else{

			return PAGINA_DETALHES_INICIACAO_ACADEMICA;
		}

	}
	
	
	@RequestMapping(value = {"consolidar/{idInscricao}"}, method = RequestMethod.GET)
	public String consolidarInscricao(@PathVariable("idInscricao") Integer idInscricao,Model model, RedirectAttributes redirect, Authentication auth){
		Inscricao inscricao = inscricaoRepository.findById(idInscricao);
		List<Selecao> selecoes = selecaoRepository.findAll();
		
		Aluno aluno = alunoRepository.findByCpf(auth.getName());
		
		if(aluno.equals(inscricao.getAluno())){
			inscricao.setConsolidacao(true);
			inscricaoRepository.save(inscricao);
			redirect.addFlashAttribute(INFO,MENSAGEM_SUCESSO_INSCRICAO_CONSOLIDADA);

			model.addAttribute("selecoes", selecoes);
			redirect.addFlashAttribute(ABA_SELECIONADA,INSCRICAO_TAB);
			
			
			/*Runnable enviarEmail = new Runnable() {
				@Override
				public void run() {		
					Email email=new Email();
					String from=FROM;
					String to=inscricao.getAluno().getPessoa().getEmail();
					String body = BODY;							
					email.setFrom(from);
					email.setSubject(ASSUNTO);
					email.setText(body);
					email.setTo(to);

					try {

						emailService.sendEmail(email);

					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}
			};*/

			/*Thread threadEnviarEmail = new Thread(enviarEmail);
			threadEnviarEmail.start();*/
			
			return REDIRECT_PAGINA_DETALHES_INSCRICAO + idInscricao;
			
		} else{
			redirect.addFlashAttribute(ERRO,MENSAGEM_FALTA_DE_PERMISSAO);
			
			return REDIRECT_PAGINA_ALUNO_LISTAR_SELECAO;
			
		}
		

	}


	@RequestMapping(value = "consolidar", method = RequestMethod.GET,  produces=  MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Model consolidarViaAjax(@RequestParam("idInscricao") Integer idInscricao, @RequestParam("consolidacao") boolean consolidacao,Model model){
		inscricaoRepository.consolidar(idInscricao, consolidacao);
		model.addAttribute(RESULTADO,SUCESSO);
		return model;

	}

	@RequestMapping(value = "consolidarTodos", method = RequestMethod.GET,  produces=  MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Model consolidarTodos(@RequestParam("idSelecao") Integer idSelecao,@RequestParam("consolidacao") boolean consolidacao,Model model){
		inscricaoRepository.consolidarTodos(idSelecao, consolidacao);
		model.addAttribute(RESULTADO,SUCESSO);
		return model;
	}
}
