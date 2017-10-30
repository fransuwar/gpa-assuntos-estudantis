package br.ufc.npi.auxilio.controller;

import static br.ufc.npi.auxilio.utils.Constants.ALUNO;
import static br.ufc.npi.auxilio.utils.Constants.ERRO;
import static br.ufc.npi.auxilio.utils.Constants.INFO;
import static br.ufc.npi.auxilio.utils.Constants.PERMISSAO_COORDENADOR;
import static br.ufc.npi.auxilio.utils.Constants.PERMISSAO_SERVIDOR;
import static br.ufc.npi.auxilio.utils.Constants.NUM_CARACTERES;
import static br.ufc.npi.auxilio.utils.SuccessMessageConstants.MSG_SUCESSO_DOCUMENTO_ADICIONADO;
import static br.ufc.npi.auxilio.utils.SuccessMessageConstants.MSG_SUCESSO_DOCUMENTO_REMOVIDO;
import static br.ufc.npi.auxilio.utils.SuccessMessageConstants.MSG_SUCESSO_VISITA;
import static br.ufc.npi.auxilio.utils.ErrorMessageConstants.MSG_ERRO_ANALISE_DOCUMENTACAO;

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
import br.ufc.npi.auxilio.model.Selecao;
import br.ufc.npi.auxilio.model.Servidor;
import br.ufc.npi.auxilio.model.VisitaDomiciliar;
import br.ufc.npi.auxilio.service.AlunoService;
import br.ufc.npi.auxilio.service.InscricaoService;
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
	
	@Autowired
	private InscricaoService inscricaoService;

	//@PreAuthorize(PERMISSAO_COORDENADOR)
	@PreAuthorize(PERMISSAO_COORDENADOR + " or " + PERMISSAO_SERVIDOR)
	@GetMapping("/{inscricao}")
	public String cadastrar(@PathVariable Inscricao inscricao, Authentication auth, Model model) {
		Aluno aluno = alunoService.buscarPorCpf(inscricao.getAluno().getPessoa().getCpf());	
		VisitaDomiciliar visitaDomiciliar = new VisitaDomiciliar();
		List<Servidor> servidores = inscricao.getSelecao().getComissao();
		
		model.addAttribute("servidor", servidores);
		
		if(inscricao.getVisitaDomiciliar() ==  null){
			model.addAttribute("visita", visitaDomiciliar );
			model.addAttribute("acao", "cadastrar");
			model.addAttribute("servidor", servidores);
		}else{
			visitaDomiciliar = visitaService.buscar(inscricao.getVisitaDomiciliar().getId());
			model.addAttribute("servidor", servidorService.getById(visitaDomiciliar.getResponsavel().getId()));
			model.addAttribute("visita", visitaDomiciliar);
			model.addAttribute("acao", "editar");
		}
		
		//servidorService.getById(visitaDomiciliar.getResponsavel().getId())
		
		
		model.addAttribute("inscrição", inscricao != null ? inscricao.getId() : null);
		model.addAttribute(ALUNO, aluno);
		model.addAttribute("selecao", inscricao.getSelecao());
	
		return PageConstants.PAGINA_VISITA;
	}
	@PreAuthorize(PERMISSAO_COORDENADOR + " or " + PERMISSAO_SERVIDOR)
	@PostMapping("/{inscricao}")
	public String cadastrar(@RequestParam("imagensVisita") List<MultipartFile> imagens,
			@RequestParam("formularioVisita") List<MultipartFile> formulario, VisitaDomiciliar visita ,@PathVariable("inscricao") Inscricao inscricao,
			Model model, Authentication auth, RedirectAttributes redirect, Selecao selecao, @RequestParam("servidor") Servidor servidor) {
		
		servidor = servidorService.getById(servidor.getPessoa().getId());
		if(visita.getObservacoes().length() > NUM_CARACTERES){
			redirect.addFlashAttribute(ERRO, MSG_ERRO_ANALISE_DOCUMENTACAO);
			return RedirectConstants.REDIRECT_VISITA_DOMICILIAR + inscricao.getId();
		}
		VisitaDomiciliar visitaDomiciliar = visitaService.buscar(visita.getId());
		if(visitaDomiciliar ==  null){
			visitaDomiciliar = visita;
		}else{

			visitaDomiciliar.setData(visita.getData());
			visitaDomiciliar.setObservacoes(visita.getObservacoes());
			visitaDomiciliar.setRelatorio(visita.getRelatorio());
			visitaDomiciliar.setResultado(visita.getResultado());
			//visitaDomiciliar.setResponsave(servidorService.getByCpf(auth.getName()));
			visitaDomiciliar.setResponsavel(servidor);
			
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
						if (visitaDomiciliar.getFormulario() != null)
							visitaService.excluirFormulario(visitaDomiciliar);
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
		
		visitaDomiciliar.setResponsavel(servidor);
		visitaService.salvar(visitaDomiciliar);
		redirect.addFlashAttribute(INFO, MSG_SUCESSO_VISITA);
		inscricaoService.salvar(inscricao);
		return RedirectConstants.REDIRECT_VISITA_DOMICILIAR + inscricao.getId();
	}
	
	
	//@PreAuthorize(PERMISSAO_COORDENADOR)
	@PreAuthorize(PERMISSAO_COORDENADOR + " or " + PERMISSAO_SERVIDOR)
	@GetMapping("/documento/{inscricao}/download/{arquivo}")
	public HttpEntity<?> downloadDocumento(@PathVariable Inscricao inscricao, @PathVariable Documento arquivo, RedirectAttributes redirect) {
		try {
			if(inscricao != null && arquivo != null) {
				return visitaService.downloadDocumento(visitaService.buscarDocumento(arquivo), "attachment");
			}
		} catch (AuxilioMoradiaException e) {
			redirect.addFlashAttribute(ERRO, e.getMessage());
		}
		return null;
	}
	
	//@PreAuthorize(PERMISSAO_COORDENADOR)
	@PreAuthorize(PERMISSAO_COORDENADOR + " or " + PERMISSAO_SERVIDOR)
	@GetMapping("/documento/{inscricao}/excluir/{arquivo}")
	public String excluirImagem(@PathVariable Inscricao inscricao, @PathVariable Documento arquivo, RedirectAttributes redirect) {
		try {
			if(inscricao != null && arquivo != null) {
				visitaService.excluirDocumento(inscricao.getVisitaDomiciliar(), visitaService.buscarDocumento(arquivo));
				redirect.addFlashAttribute(INFO, MSG_SUCESSO_DOCUMENTO_REMOVIDO);
			}
		} catch (AuxilioMoradiaException e) {
			redirect.addFlashAttribute(ERRO, e.getMessage());
		}
		return RedirectConstants.REDIRECT_VISITA_DOMICILIAR + inscricao.getId();
	}
	
	//@PreAuthorize(PERMISSAO_COORDENADOR)
	@PreAuthorize(PERMISSAO_COORDENADOR + " or " + PERMISSAO_SERVIDOR)
	@GetMapping("/documento/{inscricao}/excluirformulario")
	public String excluirFormulario(@PathVariable Inscricao inscricao, RedirectAttributes redirect) {
		try {
			if(inscricao != null && inscricao.getVisitaDomiciliar().getFormulario() != null) {
				visitaService.excluirFormulario(inscricao.getVisitaDomiciliar());
				redirect.addFlashAttribute(INFO, MSG_SUCESSO_DOCUMENTO_REMOVIDO);
			}
		} catch (AuxilioMoradiaException e) {
			redirect.addFlashAttribute(ERRO, e.getMessage());
		}
		return RedirectConstants.REDIRECT_VISITA_DOMICILIAR + inscricao.getId();
	}

	@ModelAttribute("resultados")
	public Resultado[] getResultados() {
		return Resultado.values();
	}
	
	@ModelAttribute("servidores")
	public List<Servidor> getAllServidores() {
		return servidorService.getAll();
	}
		
}