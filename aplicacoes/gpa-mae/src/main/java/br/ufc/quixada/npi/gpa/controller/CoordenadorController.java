package br.ufc.quixada.npi.gpa.controller;

import static br.ufc.quixada.npi.gpa.utils.Constants.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.npi.gpa.enums.Status;
import br.ufc.quixada.npi.gpa.enums.TipoSelecao;
import br.ufc.quixada.npi.gpa.model.Documento;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.model.Servidor;
import br.ufc.quixada.npi.gpa.service.DocumentoService;
import br.ufc.quixada.npi.gpa.service.SelecaoService;
import br.ufc.quixada.npi.gpa.service.ServidorService;

@Controller
@RequestMapping("coordenador")
public class CoordenadorController {

	@Inject
	private SelecaoService selecaoService;
	
	@Inject
	private DocumentoService documentoService;
	
	@Inject
	private ServidorService servidorService;
	
	@RequestMapping(value = { "selecao/listar" }, method = RequestMethod.GET)
	public String listarSelecoes(ModelMap model, HttpServletRequest request, Authentication auth){
		
		List<Selecao> selecoes = this.selecaoService.find(Selecao.class);

		model.addAttribute("selecoes", selecoes);
		model.addAttribute("tipoSelecao", TipoSelecao.values());
		model.addAttribute("inic_acad", TipoSelecao.INIC_ACAD);
		model.addAttribute("aux_mor", TipoSelecao.AUX_MOR);
		
		return "coordenador/coordenacao";
	}
	
	@RequestMapping(value = { "selecao/cadastrar" }, method = RequestMethod.GET)
	public String cadastroSelecao(Model model) {
		
		model.addAttribute("action", "cadastrar");
		model.addAttribute("tipoSelecao", TipoSelecao.values());
		model.addAttribute("selecao", new Selecao());
		
		return PAGINA_CADASTRAR_SELECAO;

	}
	
	@RequestMapping(value = { "selecao/cadastrar" }, method = RequestMethod.POST)
	public String cadastroSelecao(@RequestParam("files") List<MultipartFile> files, Model model,
			@Valid @ModelAttribute("selecao") Selecao selecao, BindingResult result, Authentication auth, 
			RedirectAttributes redirect) {
		
		model.addAttribute("action", "cadastrar");

		if (selecao != null && selecao.getAno() != null) {
			if (selecao.getAno() < DateTime.now().getYear()) {
				result.rejectValue("ano", "selecao.ano", MENSAGEM_ERRO_ANO_SELECAO_CADASTRAR);
			}
		}
		
		if (selecao != null && selecao.getDataInicio() != null && selecao.getDataTermino() != null) {
			if ((new DateTime(selecao.getDataTermino())).isBefore(new DateTime(selecao.getDataInicio()))) {
				result.rejectValue("dataTermino", "selecao.dataTermino", MENSAGEM_ERRO_DATATERMINO_SELECAO_CADASTRAR);
			}
		}
		
		if (selecao != null)  {
			if (selecaoService.isSelecaoCadastrada(selecao)) {
				result.rejectValue("sequencial", "selecao.sequencial", "Número do edital com esse tipo de selecao já existente");

			}
		}
		
		if (result.hasErrors()) {
			model.addAttribute("selecao", selecao);
			model.addAttribute("tipoSelecao", TipoSelecao.values());

			return PAGINA_CADASTRAR_SELECAO;
		}
		
		List<Documento> documentos = new ArrayList<Documento>();
		if (files != null && !files.isEmpty() && files.get(0).getSize() > 0) { 
			for (MultipartFile mfiles : files) {
				try {
					
					if (mfiles.getBytes() != null && mfiles.getBytes().length != 0) {
						
						Documento documento = new Documento();
						documento.setArquivo(mfiles.getBytes());
						documento.setNome(mfiles.getOriginalFilename());
						documento.setTipo(mfiles.getContentType());
						documento.setSelecao(selecao);
						documentos.add(documento);
					}
					
					
				} catch (IOException ioe) {
					model.addAttribute("erro", MENSAGEM_ERRO_SALVAR_DOCUMENTOS);

					return PAGINA_CADASTRAR_SELECAO;
				}
			} 
			
			if (!documentos.isEmpty()) {
				selecao.setDocumentos(documentos);
			}
			
		} else {
			
			model.addAttribute("tipoSelecao", TipoSelecao.values());
			model.addAttribute("anexoError", MENSAGEM_ERRO_ANEXO);

			return PAGINA_CADASTRAR_SELECAO;
		}
		Servidor coordenador = servidorService.getServidor(auth.getName());
		selecao.addCoordenador(coordenador);
		selecao.setResponsavel(coordenador);
		this.selecaoService.save(selecao);
		redirect.addFlashAttribute("info", MENSAGEM_SUCESSO_SELECAO_CADASTRADA);
		return REDIRECT_PAGINA_LISTAR_SELECAO;
		
	}
	
	@RequestMapping(value = { "selecao/editar/{idSelecao}" }, method = RequestMethod.GET)
	public String editarSelecao(@PathVariable("idSelecao") Integer idSelecao, Model model, RedirectAttributes redirect) {
		
		Selecao selecao = this.selecaoService.getSelecaoComDocumentos(idSelecao);

		if (selecao != null && selecao.getStatus() != null) {
		
			model.addAttribute("action", "editar");
			model.addAttribute("tipoSelecao", TipoSelecao.values());
			model.addAttribute("selecao", selecao);
			
		} else {
			redirect.addFlashAttribute("erro", MENSAGEM_PERMISSAO_NEGADA);
			return REDIRECT_PAGINA_LISTAR_SELECAO;
		}
		
		return PAGINA_CADASTRAR_SELECAO;

	}
	
	@RequestMapping(value = { "selecao/editar" }, method = RequestMethod.POST)
	public String editarSelecao(@RequestParam("files") List<MultipartFile> files,
			@Valid @ModelAttribute("selecao") Selecao selecao, Model model,
			BindingResult result, RedirectAttributes redirect, HttpServletRequest request) {
		
		model.addAttribute("action", "editar");
		
		if (selecao != null && selecao.getAno() != null) {
			if (selecao.getAno() < DateTime.now().getYear()) {
				result.rejectValue("ano", "selecao.ano", MENSAGEM_ERRO_ANO_SELECAO_CADASTRAR);
			}
		}
		
		if (selecao != null && selecao.getDataInicio() != null && selecao.getDataTermino() != null) {
			if ((new DateTime(selecao.getDataTermino())).isBefore(new DateTime(selecao.getDataInicio()))) {
				result.rejectValue("dataTermino", "selecao.dataTermino", MENSAGEM_ERRO_DATATERMINO_SELECAO_CADASTRAR);
			}
		}
		
		if (result.hasErrors()) {
			model.addAttribute("selecao", selecao);
			model.addAttribute("tipoSelecao", TipoSelecao.values());

			return PAGINA_CADASTRAR_SELECAO;
		}
		
		String doc[] = request.getParameterValues("doc");

		if (doc != null) {

			if (selecaoService.getSelecaoComDocumentos(selecao.getId()).getDocumentos().size() == doc.length
				&& (files.isEmpty() || files.get(0).getSize() <= 0)) {
				model.addAttribute("action", "editar");
				redirect.addFlashAttribute("erro", MENSAGEM_ERRO_ANEXO_EXCLUIR);

				return PAGINA_CADASTRAR_SELECAO;

			}

			for (int k = 0; k < doc.length; k++) {
				Documento d = new Documento();
				d.setId(Integer.parseInt(doc[k]));
				documentoService.delete(d);
			}
		}
		
		List<Documento> documentos = new ArrayList<Documento>();
		if (files != null && !files.isEmpty() && files.get(0).getSize() > 0) {
			for (MultipartFile mfiles : files) {
				try {
					if (mfiles.getBytes() != null && mfiles.getBytes().length != 0) {
						Documento documento = new Documento();
						documento.setArquivo(mfiles.getBytes());
						documento.setNome(mfiles.getOriginalFilename());
						documento.setTipo(mfiles.getContentType());
						documento.setSelecao(selecao);
						documentos.add(documento);
						documentoService.save(documento);
					}
				} catch (IOException ioe) {
					model.addAttribute("erro", MENSAGEM_ERRO_SALVAR_DOCUMENTOS);

					return PAGINA_CADASTRAR_SELECAO;
				}
			}
		} else {
			
			model.addAttribute("tipoSelecao", TipoSelecao.values());
			model.addAttribute("anexoError", MENSAGEM_ERRO_ANEXO);

			return PAGINA_CADASTRAR_SELECAO;
		}
		
		
		this.selecaoService.update(selecao);
		redirect.addFlashAttribute("info", MENSAGEM_SUCESSO_SELECAO_ATUALIZADA);
		
		return REDIRECT_PAGINA_LISTAR_SELECAO;
	}
	
	@RequestMapping(value = { "selecao/excluir/{idSelecao}" }, method = RequestMethod.GET)
	public String excluirSelecao(@PathVariable("idSelecao") Integer idSelecao, RedirectAttributes redirect) {
		
		Selecao selecao = this.selecaoService.find(Selecao.class, idSelecao);
		
		if (selecao != null) {
			
				if (selecao.getStatus().equals(Status.NOVA)) {
					this.selecaoService.delete(selecao);
					redirect.addFlashAttribute("info", MENSAGEM_SUCESSO_SELECAO_REMOVIDA);
				} else {
					if(selecao.getInscritos().size() == 0){
						this.selecaoService.delete(selecao);
						redirect.addFlashAttribute("info", MENSAGEM_SUCESSO_SELECAO_REMOVIDA);
					}else{
						redirect.addFlashAttribute("erro", MENSAGEM_ERRO_SELECAO_REMOVER);
					}
				}
			} else {
				redirect.addFlashAttribute("erro", MENSAGEM_ERRO_SELECAO_INEXISTENTE);
			
		}
		
		return REDIRECT_PAGINA_LISTAR_SELECAO;
	}
	
	@RequestMapping(value = "/comissao/atribuir/{idSelecao}", method = RequestMethod.GET)
	public String atribuirParecerista(@PathVariable("idSelecao") Integer idSelecao,
			Model model, RedirectAttributes redirectAttributes) {

		model.addAttribute("idSelecao", idSelecao);
		model.addAttribute("servidores", servidorService.find(Servidor.class));
		model.addAttribute("comissao", selecaoService.find(Selecao.class, idSelecao));
		
		return PAGINA_ATRIBUIR_COMISSAO;
	}

	@RequestMapping(value = "/comissao/atribuir", method = RequestMethod.POST)
	public String atribuirPareceristaNoProjeto(@RequestParam("idSelecao") Integer idSelecao,
			@RequestParam("idServidor") Integer idServidor, Model model, RedirectAttributes redirect) {


		if (idServidor == null) {
			
		redirect.addFlashAttribute("erro", MENSAGEM_ERRO_MEMBRO_COMISSAO_ATRIBUIR);

			return REDIRECT_PAGINA_ATRIBUIR_COMISSAO + idSelecao;

		} else {
			
			Selecao selecao = selecaoService.find(Selecao.class, idSelecao);
			
			List<Servidor> comissao = selecao.getMembrosComissao();
	
			Servidor servidor = this.servidorService.find(Servidor.class, idServidor);
			if (comissao.contains(servidor)) {
				
				redirect.addFlashAttribute("erro", MENSAGEM_ERRO_MEMBRO_COMISSAO_REPETICAO);
				
				return REDIRECT_PAGINA_ATRIBUIR_COMISSAO + idSelecao;
				
			} else {
				
				selecao.getMembrosComissao().add(servidor);

				selecaoService.update(selecao);

				redirect.addFlashAttribute("info", MENSAGEM_SUCESSO_COMISSAO_FORMADA);

				return REDIRECT_PAGINA_ATRIBUIR_COMISSAO + idSelecao;	
			}
		}
	}
	
	@RequestMapping(value = "/comissao/excluir/{idSelecao}/{idServidor}", method = RequestMethod.GET)
	public String excluirMembroComissao(@PathVariable("idSelecao") Integer idSelecao,@PathVariable("idServidor") Integer idServidor, 
			Model model, Authentication auth, RedirectAttributes redirect) {
		
		Selecao selecao = selecaoService.find(Selecao.class, idSelecao);
		Servidor coordenador = servidorService.getServidor(auth.getName());		
		Servidor servidor = this.servidorService.find(Servidor.class, idServidor);
		if(coordenador.getId() != servidor.getId()){
			
			selecao.getMembrosComissao().remove(servidor);
			selecaoService.update(selecao);
			redirect.addFlashAttribute("info", MENSAGEM_SUCESSO_MEMBRO_EXCLUIDO);
		}else
		
		redirect.addFlashAttribute("erro", MENSAGEM_ERRO_COMISSAO_EXCLUIR_COORDENADOR);

		return REDIRECT_PAGINA_ATRIBUIR_COMISSAO + idSelecao;

	}
	
}
