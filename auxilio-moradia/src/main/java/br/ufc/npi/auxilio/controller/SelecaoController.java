package br.ufc.npi.auxilio.controller;

import static br.ufc.npi.auxilio.utils.Constants.COORDENADOR;
import static br.ufc.npi.auxilio.utils.Constants.ERRO;
import static br.ufc.npi.auxilio.utils.Constants.INFO;
import static br.ufc.npi.auxilio.utils.PageConstants.CADASTRAR_SELECAO;
import static br.ufc.npi.auxilio.utils.RedirectConstants.REDIRECT_LISTAR_SELECAO;
import static br.ufc.npi.auxilio.utils.SuccessMessageConstants.MSG_SELECAO_CADASTRADA;
import static br.ufc.npi.auxilio.utils.SuccessMessageConstants.MSG_SUCESSO_SELECAO_REMOVIDA;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.npi.auxilio.excecao.AuxilioMoradiaException;
import br.ufc.npi.auxilio.model.Documento;
import br.ufc.npi.auxilio.model.Pessoa;
import br.ufc.npi.auxilio.model.Selecao;
import br.ufc.npi.auxilio.model.Servidor;
import br.ufc.npi.auxilio.model.TipoDocumento;
import br.ufc.npi.auxilio.service.DocumentacaoService;
import br.ufc.npi.auxilio.service.InscricaoService;
import br.ufc.npi.auxilio.service.PessoaService;
import br.ufc.npi.auxilio.service.SelecaoService;
import br.ufc.npi.auxilio.service.ServidorService;
import br.ufc.npi.auxilio.utils.Constants;
import br.ufc.npi.auxilio.utils.ErrorMessageConstants;
import br.ufc.npi.auxilio.utils.PageConstants;
import br.ufc.npi.auxilio.utils.RedirectConstants;
import br.ufc.npi.auxilio.utils.SuccessMessageConstants;

@Controller
@RequestMapping("/selecao")
public class SelecaoController {
	
	@Autowired
	private SelecaoService selecaoService;
	
	@Autowired
	private ServidorService servidorService;
	
	@Autowired
	private DocumentacaoService documentacaoService;
	
	@Autowired
	private InscricaoService inscricaoService;
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping({"", "/", "/listar"})
	public String listarSelecoes(Model model) {
		model.addAttribute("selecoes", selecaoService.getAll());
		return PageConstants.LISTAR_SELECAO;
	}
	
	@Secured(COORDENADOR)
	@GetMapping("/cadastrar")
	public String cadastrarSelecaoForm(Model model) {
		model.addAttribute("acao", "Cadastrar");
		model.addAttribute("selecao", new Selecao());
		
		return CADASTRAR_SELECAO;
	}
	
	@Secured(COORDENADOR)
	@PostMapping("/cadastrar")
	public String cadastrarSelecao(Selecao selecao, Authentication auth, Model model, RedirectAttributes redirect,
			@RequestParam("tiposDocumento") List<TipoDocumento> tiposDeDocumento) {
		selecao.setResponsavel(servidorService.getByCpf(auth.getName()));
		selecao.addAllTiposDeDocumento(tiposDeDocumento);
		try {
			selecaoService.cadastrar(selecao);
			redirect.addFlashAttribute(INFO, MSG_SELECAO_CADASTRADA);
			return REDIRECT_LISTAR_SELECAO;
		} catch (AuxilioMoradiaException e) {
			model.addAttribute(ERRO, e.getMessage());
			return CADASTRAR_SELECAO;
		}
	}
	
	@Secured(COORDENADOR)
	@GetMapping("/excluir/{selecao}")
	public String excluirSelecao(@PathVariable Selecao selecao, RedirectAttributes redirect) {
		try {
			selecaoService.excluir(selecao);
			redirect.addFlashAttribute(INFO, MSG_SUCESSO_SELECAO_REMOVIDA);
		} catch (AuxilioMoradiaException e) {
			redirect.addFlashAttribute(ERRO, e.getMessage());
		}
		return REDIRECT_LISTAR_SELECAO;
	}
	
	@Secured(COORDENADOR)
	@GetMapping("/editar/{selecao}")
	public String editarSelecao(@PathVariable Selecao selecao, Model model,
			RedirectAttributes redirect) {
		
		if (selecao != null) {
			model.addAttribute("acao", "Editar");
			model.addAttribute("selecao", selecao);
			
			return CADASTRAR_SELECAO;
		}
		return REDIRECT_LISTAR_SELECAO;
	}
	
	@GetMapping("detalhes/{selecao}")
	public ModelAndView detalhes(@PathVariable Selecao selecao, ModelAndView mav, Authentication auth){
		mav.addObject("selecao", selecao);
		mav.setViewName("selecao/detalhes");
		Pessoa pessoa = pessoaService.getByCpf(auth.getName());
		mav.addObject("inscrito", inscricaoService.estaInscrito(pessoa, selecao));
		return mav;
	}

	@RequestMapping(value="/cadastrar", params={"adicionaArquivo"})
	public String adicionarDocumento( @RequestParam("files") List<MultipartFile> files,
			Selecao selecao, Model model, @RequestParam("acao") String acao,
			@RequestParam("tiposDocumento") List<TipoDocumento> tiposDeDocumento) {
		
		if (files != null && !files.isEmpty() && files.get(0).getSize() > 0) { 
			for (MultipartFile mfiles : files) {
				try {
					if (mfiles.getBytes() != null && mfiles.getBytes().length != 0) {

						Documento documento = new Documento();
						documento.setArquivo(mfiles.getBytes());
						documento.setNome(mfiles.getOriginalFilename());
						documento.setCaminho(mfiles.getContentType());

						if(selecao.getDocumentos() == null)
							selecao.setDocumentos(new ArrayList<Documento>());
						selecao.getDocumentos().add(documento);
					}
				} catch (IOException e)	{
					
				}
			} 
		}
		selecao.addAllTiposDeDocumento(tiposDeDocumento);
		model.addAttribute("selecao", selecao);
		model.addAttribute("acao", acao);
		return PageConstants.CADASTRAR_SELECAO;
	}
	
	@GetMapping("/comissao/{idSelecao}")
	public String formGerenciarComissao(@PathVariable("idSelecao") Selecao selecao, Authentication auth, Model model) {
		model.addAttribute( "selecao", selecao );
		model.addAttribute( "coordenador", servidorService.getByCpf( auth.getName() ) );
		return PageConstants.GERENCIAR_COMISSAO;
	}
	
	@PostMapping(value = "/comissao/{idSelecao}", params = {"adicionarServidor"})
	public String adicionarMembroComissao( @PathVariable("idSelecao") Selecao selecao,
			@RequestParam("idServidor") Servidor servidor, Model model, RedirectAttributes redirect ) {
		selecao.getComissao().add(servidor);
		try {
			selecaoService.cadastrar(selecao);
		} catch (AuxilioMoradiaException e) {
			redirect.addFlashAttribute(Constants.ERRO, e.getMessage());
			return RedirectConstants.REDIRECT_GERENCIAR_COMISSAO + selecao.getId();
		}
		redirect.addFlashAttribute("selecao", selecao);
		return RedirectConstants.REDIRECT_GERENCIAR_COMISSAO + selecao.getId();
	}
	
	@GetMapping("/comissao/excluir/{idSelecao}/{idServidor}")
	public String excluirMembroComissao(@PathVariable("idSelecao") Selecao selecao, 
			@PathVariable("idServidor") Servidor servidor, 
			Authentication auth, RedirectAttributes redirect) {	

		Servidor coordenador = servidorService.getByCpf(auth.getName());			

		if(coordenador.getId() != servidor.getId()){
			selecao.getComissao().remove(servidor);
			try {
				selecaoService.cadastrar(selecao);
			} catch (AuxilioMoradiaException e) {
				redirect.addFlashAttribute(ERRO, e.getMessage());
			}
			redirect.addFlashAttribute(INFO, SuccessMessageConstants.MSG_SUCESSO_MEMBRO_EXCLUIDO);
		} else {
			redirect.addFlashAttribute(ERRO, ErrorMessageConstants.ERRO_COMISSAO_EXCLUIR_COORDENADOR);
		}
		
		return RedirectConstants.REDIRECT_GERENCIAR_COMISSAO + selecao.getId();
	}
	
	@ModelAttribute("tiposDeDocumento")
	public List<TipoDocumento> getTiposDeDocumento() {
		return documentacaoService.getAllTipoDocumento();
	}

	@ModelAttribute("servidores")
	public List<Servidor> getAllServidores() {
		return servidorService.getAll();
	}
}
