package br.ufc.npi.auxilio.controller;

import static br.ufc.npi.auxilio.utils.Constants.ALUNO;
import static br.ufc.npi.auxilio.utils.Constants.ERRO;
import static br.ufc.npi.auxilio.utils.Constants.INFO;
import static br.ufc.npi.auxilio.utils.Constants.PERMISSAO_COORDENADOR;
import static br.ufc.npi.auxilio.utils.SuccessMessageConstants.MSG_SUCESSO_DOCUMENTO_ADICIONADO;
import static br.ufc.npi.auxilio.utils.SuccessMessageConstants.MSG_SUCESSO_VISITA;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.npi.auxilio.enums.Resultado;
import br.ufc.npi.auxilio.excecao.AuxilioMoradiaException;
import br.ufc.npi.auxilio.model.Aluno;
import br.ufc.npi.auxilio.model.Documento;
import br.ufc.npi.auxilio.model.Inscricao;
import br.ufc.npi.auxilio.model.Servidor;
import br.ufc.npi.auxilio.model.VisitaDomiciliar;
import br.ufc.npi.auxilio.service.AlunoService;
import br.ufc.npi.auxilio.service.ServidorService;
import br.ufc.npi.auxilio.service.VisitaService;
import br.ufc.npi.auxilio.utils.ErrorMessageConstants;
import br.ufc.npi.auxilio.utils.PageConstants;
import br.ufc.npi.auxilio.utils.RedirectConstants;

@Controller
@RequestMapping("visita")
public class VisitaController {

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private ServidorService servidorService;

	@Autowired
	private VisitaService visitaService;

	@PreAuthorize(PERMISSAO_COORDENADOR)
	@GetMapping("/{inscricao}")
	public String cadastrar(@PathVariable Inscricao inscricao, Authentication auth, Model model) {
		Aluno aluno = alunoService.buscarPorCpf(inscricao.getAluno().getPessoa().getCpf());
		Servidor servidor = servidorService.getByCpf(auth.getName());
		
		if(inscricao.getVisitaDomiciliar() ==  null){
			model.addAttribute("visita", new VisitaDomiciliar());
			model.addAttribute("acao", "cadastrar");
		}else{
			VisitaDomiciliar visitaDomiciliar = visitaService.buscar(inscricao.getVisitaDomiciliar().getId());
			model.addAttribute("visita", visitaDomiciliar);
			model.addAttribute("acao", "editar");
		}
		model.addAttribute("servidor", servidor);
		model.addAttribute("inscrição", inscricao != null ? inscricao.getId() : null);
		model.addAttribute(ALUNO, aluno);
		model.addAttribute("selecao", inscricao.getSelecao());
	
		return PageConstants.PAGINA_VISITA;
	}
	
	@PreAuthorize(PERMISSAO_COORDENADOR)
	@PostMapping("/{inscricao}")
	public String cadastrar(@RequestParam("imagensVisita") List<MultipartFile> imagens,
			@RequestParam("formularioVisita") List<MultipartFile> formulario, VisitaDomiciliar visita ,@PathVariable("inscricao") Inscricao inscricao,
			Model model, Authentication auth, RedirectAttributes redirect) {
		
		
		VisitaDomiciliar visitaDomiciliar = visitaService.buscar(visita.getId());
		if(visitaDomiciliar ==  null){
			visitaDomiciliar = visita;
		}else{

			visitaDomiciliar.setData(visita.getData());
			visitaDomiciliar.setObservacoes(visita.getObservacoes());
			visitaDomiciliar.setRelatorio(visita.getRelatorio());
			visitaDomiciliar.setResultado(visita.getResultado());		
			visitaDomiciliar.setResponsavel(servidorService.getByCpf(auth.getName()));
			
		}
		// Salvar Imagens
		if (imagens != null && !imagens.isEmpty() && imagens.get(0).getSize() > 0) {
			for (MultipartFile mfiles : imagens) {
				try {
					if (mfiles.getBytes() != null && mfiles.getBytes().length != 0) {
						visitaService.adicionarImagens(visitaDomiciliar, mfiles);
						redirect.addFlashAttribute(INFO, MSG_SUCESSO_DOCUMENTO_ADICIONADO);
					}
				} catch (IOException e)	{
					redirect.addFlashAttribute(ERRO, ErrorMessageConstants.MENSAGEM_ERRO_SALVAR_DOCUMENTOS);
				} catch (AuxilioMoradiaException e) {
					redirect.addFlashAttribute(ERRO, e.getMessage());
				}
			}
		}
		// Salvar Formulário
		if (formulario != null && !formulario.isEmpty() && formulario.get(0).getSize() > 0) {
			for (MultipartFile mfiles : formulario) {
				try {
					if (mfiles.getBytes() != null && mfiles.getBytes().length != 0) {
						visitaService.adicionarFormulario(visitaDomiciliar, mfiles);
						redirect.addFlashAttribute(INFO, MSG_SUCESSO_DOCUMENTO_ADICIONADO);
					}
				} catch (IOException e)	{
					redirect.addFlashAttribute(ERRO, ErrorMessageConstants.MENSAGEM_ERRO_SALVAR_DOCUMENTOS);
				} catch (AuxilioMoradiaException e) {
					redirect.addFlashAttribute(ERRO, e.getMessage());
				}
			}
		}
		if(inscricao.getVisitaDomiciliar() == null){
			inscricao.setVisitaDomiciliar(visitaDomiciliar);
		}
		
		visitaDomiciliar.setResponsavel(servidorService.getByCpf(auth.getName()));
		visitaService.salvar(visitaDomiciliar);
		redirect.addFlashAttribute(INFO, MSG_SUCESSO_VISITA);
		return RedirectConstants.REDIRECT_PAGINA_VISITA + inscricao.getId();
	}
	
	
	@PreAuthorize(PERMISSAO_COORDENADOR)
	@GetMapping("/documento/{inscricao}/download/{documento}")
	public HttpEntity<?> downloadDocumento(@PathVariable Inscricao inscricao, @PathVariable Documento documento, RedirectAttributes redirect) {
		try {
			if(inscricao != null && documento != null) {
				documento = visitaService.buscarDocumento(documento);
				return visitaService.downloadDocumento(documento, "attachment");
			}
		} catch (AuxilioMoradiaException e) {
			redirect.addFlashAttribute(ERRO, e.getMessage());
		}
		return null;
	}

	@ModelAttribute("resultados")
	public Resultado[] getResultados() {
		return Resultado.values();
	}
}