package br.ufc.quixada.npi.gpa.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.npi.gpa.enums.Curso;
import br.ufc.quixada.npi.gpa.enums.EstadoMoradia;
import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.model.VisitaDomiciliar;
import br.ufc.quixada.npi.gpa.service.AlunoService;
import br.ufc.quixada.npi.gpa.service.InscricaoService;
import br.ufc.quixada.npi.gpa.utils.Constants;


@Controller
@RequestMapping("detalhesInscricao")
@SessionAttributes({ Constants.USUARIO_ID, Constants.USUARIO_LOGADO})
public class detalhesInscricaoController {
	
	@Inject
	private InscricaoService inscricaoService;
	
	@RequestMapping("detalhes/inscricao/fotoAluno/{idInscricao}")
	public void pegarAlunoFoto(@PathVariable("idInscricao") Integer idInscricao, HttpServletResponse response){
		Inscricao inscricao = this.inscricaoService.find(Inscricao.class, idInscricao);
		
		try {
			response.setContentType("image/jpg");
			java.io.OutputStream out = response.getOutputStream();
			out.write(inscricao.getQuestionarioAuxilioMoradia().getFoto());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {		
			try {
				response.setContentType("text/html");
				response.sendRedirect("../../../../resources/img/alunoImage.png");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}

}
