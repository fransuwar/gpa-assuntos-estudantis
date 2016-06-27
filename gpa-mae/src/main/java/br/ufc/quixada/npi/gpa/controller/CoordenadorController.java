package br.ufc.quixada.npi.gpa.controller;

import static br.ufc.quixada.npi.gpa.utils.Constants.DOCUMENTOS;
import static br.ufc.quixada.npi.gpa.utils.Constants.ERRO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_ANEXO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_ANO_SELECAO_CADASTRAR;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_COMISSAO_EXCLUIR_COORDENADOR;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_DATATERMINO_SELECAO_CADASTRAR;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_EXCLUIR_SELECAO_COM_INSCRITOS;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_EXCLUIR_TIPO_DOCUMENTO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_EXCLUIR_TIPO_DOCUMENTO_EM_USO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_MEMBRO_COMISSAO_REPETICAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_SALVAR_DOCUMENTOS;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_SEQUENCIAL_SELECAO_CADASTRAR;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_ERRO_TIPO_BOLSA;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_SUCESSO_COMISSAO_FORMADA;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_SUCESSO_MEMBRO_EXCLUIDO;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_SUCESSO_SELECAO_ATUALIZADA;
import static br.ufc.quixada.npi.gpa.utils.Constants.MENSAGEM_SUCESSO_SELECAO_REMOVIDA;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_ADICIONAR_ARQUIVO;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_ATRIBUIR_COMISSAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_CADASTRAR_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_GERENCIAR_DOCUMENTOS;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_LISTAR_SELECAO_SERVIDOR;
import static br.ufc.quixada.npi.gpa.utils.Constants.PAGINA_RELATORIO_FINAL;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_ADICIONAR_ARQUIVO;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_ATRIBUIR_COMISSAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_DETALHES_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_GERENCIAR_DOCUMENTOS;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_LISTAR_SELECAO;
import static br.ufc.quixada.npi.gpa.utils.Constants.REDIRECT_PAGINA_LISTAR_SELECAO_SERVIDOR;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.joda.time.DateTime;
import org.springframework.orm.jpa.JpaSystemException;
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

import br.ufc.quixada.npi.gpa.enums.TipoSelecao;
import br.ufc.quixada.npi.gpa.model.Documento;
import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.model.Servidor;
import br.ufc.quixada.npi.gpa.model.TipoDocumento;
import br.ufc.quixada.npi.gpa.repository.DocumentoRepository;
import br.ufc.quixada.npi.gpa.repository.InscricaoRepository;
import br.ufc.quixada.npi.gpa.repository.TipoDocumentoRepository;
import br.ufc.quixada.npi.gpa.service.InscricaoService;
import br.ufc.quixada.npi.gpa.service.SelecaoService;
import br.ufc.quixada.npi.gpa.service.ServidorService;
import br.ufc.quixada.npi.gpa.utils.Constants;


@Controller
@RequestMapping("coordenador")
public class CoordenadorController {
	
	@Inject
	private SelecaoService selecaoService;

	@Inject
	private ServidorService servidorService;
	
	@Inject
	private InscricaoService inscricaoService;
	
	@Inject
	private InscricaoRepository inscricaoRepository;
	
	@Inject
	private DocumentoRepository documentoRepository;
	
	@Inject
	private TipoDocumentoRepository tipoDocumentoRepository;
	
	@RequestMapping(value = "excluir-tipo-documento/{id}", method = RequestMethod.GET)
	public String excluirTipoDocumento(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirect) {
		
		TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(id);

		if (tipoDocumento != null){
			try{
				tipoDocumentoRepository.delete(tipoDocumento);
			}catch(JpaSystemException | PersistenceException | ConstraintViolationException e){
				redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_EXCLUIR_TIPO_DOCUMENTO_EM_USO);
			}
		}
		else{ 
			model.addAttribute("Error", MENSAGEM_ERRO_EXCLUIR_TIPO_DOCUMENTO);
		}
		model.addAttribute(DOCUMENTOS,tipoDocumentoRepository.findAll());
		return  REDIRECT_PAGINA_GERENCIAR_DOCUMENTOS;
		
		
	}
	
	@RequestMapping(value = "adicionar-tipo-arquivo", method = RequestMethod.GET)
	public String addTipoArquivo(TipoDocumento tipoDocumento){
		if(!tipoDocumento.getNome().isEmpty()){
			tipoDocumentoRepository.save(tipoDocumento);
		}
		return REDIRECT_PAGINA_GERENCIAR_DOCUMENTOS;
	}
	
	
	@RequestMapping(value = "gerenciarDocumentos", method = RequestMethod.GET)
	public String gerenciarDocumentos(ModelMap model){
		model.addAttribute(DOCUMENTOS,tipoDocumentoRepository.findAll() );
		return PAGINA_GERENCIAR_DOCUMENTOS;
	}
	
	
	@RequestMapping(value = { "selecao/listar" }, method = RequestMethod.GET)
	public String listarSelecoes(ModelMap model, HttpServletRequest request, Authentication auth){

		List<Selecao> selecoes = this.selecaoService.getSelecoes();

		model.addAttribute("selecoes", selecoes);
		model.addAttribute("tipoSelecao", TipoSelecao.values());
		model.addAttribute("inic_acad", TipoSelecao.INIC_ACAD);
		model.addAttribute("aux_mor", TipoSelecao.AUX_MOR);

		return PAGINA_LISTAR_SELECAO_SERVIDOR;
	}

	@RequestMapping(value = { "selecao/cadastrar" }, method = RequestMethod.GET)
	public String cadastroSelecao(Model model) {
		
		List<TipoDocumento> tiposDeDocumento = tipoDocumentoRepository.findAll();

		model.addAttribute("action", "cadastrar");
		model.addAttribute("selecao", new Selecao());
		model.addAttribute("tiposDeDocumento",tiposDeDocumento);

		return PAGINA_CADASTRAR_SELECAO;

	}

	@RequestMapping(value = { "selecao/cadastrar" }, method = RequestMethod.POST)
	public String cadastroSelecao(Model model,	@Valid @ModelAttribute("selecao") Selecao selecao, 
			@RequestParam("checkDocumentos[]") List<Integer> idstiposDocumentos,BindingResult result, Authentication auth, RedirectAttributes redirect) {
		
		Integer proxSequencial = selecaoService.getUltimoSequencialPorAno(selecao);
		List<TipoDocumento> tiposDeDocumento = tipoDocumentoRepository.findAll();
		
		selecao.setSequencial(proxSequencial);

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
			if (selecaoService.SelecaoEstaCadastrada(selecao)) {
				result.rejectValue("sequencial", "selecao.sequencial", MENSAGEM_ERRO_SEQUENCIAL_SELECAO_CADASTRAR);
			}			
			if(selecao.getTipoSelecao()==null){
				result.rejectValue("tipoSelecao", "selecao.tipoSelecao", MENSAGEM_ERRO_TIPO_BOLSA);
			}			
		}

		if (result.hasErrors()) {
			model.addAttribute("selecao", selecao);
			model.addAttribute("tiposDeDocumento",tiposDeDocumento);
			return PAGINA_CADASTRAR_SELECAO;
		}

		Servidor coordenador = servidorService.getServidorPorCpf(auth.getName());

		if(selecao.getResponsavel() == null){
			selecao.addCoordenador(coordenador);
			selecao.setResponsavel(coordenador);
		}
		
		if(idstiposDocumentos != null ){
			
			List<TipoDocumento> documentoSelecionados = new ArrayList<TipoDocumento>();
			
			for(Integer id: idstiposDocumentos){
				TipoDocumento documento = tipoDocumentoRepository.findById(id);
				documentoSelecionados.add(documento);
			}
			
			selecao.setTiposDeDocumento(documentoSelecionados);
			
		}

		this.selecaoService.save(selecao);
		return REDIRECT_PAGINA_DETALHES_SELECAO + selecao.getId();

	}

	@RequestMapping(value = { "selecao/editar/{idSelecao}" }, method = RequestMethod.GET)
	public String editarSelecao(@PathVariable("idSelecao") Integer idSelecao, Model model, RedirectAttributes redirect) {
		Selecao selecao = selecaoService.getSelecaoPorId(idSelecao);
		List<TipoDocumento> tiposDeDocumento = tipoDocumentoRepository.findAll();

		if (selecao != null) {
			model.addAttribute("action", "editar");
			model.addAttribute("tipoSelecao", TipoSelecao.values());
			model.addAttribute("selecao", selecao);
			model.addAttribute("membrosComissao", selecao.getMembrosComissao());
			model.addAttribute("tiposDeDocumento",tiposDeDocumento);
			
			return PAGINA_CADASTRAR_SELECAO;
		}


		return PAGINA_CADASTRAR_SELECAO;

	}

	@RequestMapping(value = { "selecao/editar" }, method = RequestMethod.POST)
	public String editarSelecao(@Valid @ModelAttribute("selecao") Selecao selecaoAtualizada, BindingResult result,
			Model model, RedirectAttributes redirect, @RequestParam("checkDocumentos[]") List<Integer> idstiposDocumentos) {


		model.addAttribute("action", "editar");

		if (selecaoAtualizada != null && selecaoAtualizada.getDataInicio() != null && selecaoAtualizada.getDataTermino() != null) {
			if ((new DateTime(selecaoAtualizada.getDataTermino())).isBefore(new DateTime(selecaoAtualizada.getDataInicio()))) {
				result.rejectValue("dataTermino", "selecao.dataTermino", MENSAGEM_ERRO_DATATERMINO_SELECAO_CADASTRAR);
			}
		}

		if (result.hasErrors()) {
			model.addAttribute("selecao", selecaoAtualizada);
			model.addAttribute("tipoSelecao", TipoSelecao.values());

			return PAGINA_CADASTRAR_SELECAO;
		}

		
		Selecao selecao = selecaoService.getSelecaoPorId(selecaoAtualizada.getId());
		
        if(idstiposDocumentos != null ){
			
			List<TipoDocumento> documentoSelecionados = new ArrayList<TipoDocumento>();
			
			for(Integer id: idstiposDocumentos){
				TipoDocumento documento = tipoDocumentoRepository.findById(id);
				documentoSelecionados.add(documento);
			}
			
			selecao.setTiposDeDocumento(documentoSelecionados);
			
		}

		selecao.setAno(selecaoAtualizada.getAno());
		selecao.setQuantidadeVagas(selecaoAtualizada.getQuantidadeVagas());
		selecao.setDataInicio(selecaoAtualizada.getDataInicio());
		selecao.setDataTermino(selecaoAtualizada.getDataTermino());
		selecao.setTipoSelecao(selecaoAtualizada.getTipoSelecao());

		this.selecaoService.update(selecao);

		redirect.addFlashAttribute("info", MENSAGEM_SUCESSO_SELECAO_ATUALIZADA);

		return REDIRECT_PAGINA_LISTAR_SELECAO_SERVIDOR;
	}

	@RequestMapping(value =  "selecao/excluir/{idSelecao}" , method = RequestMethod.GET)
	public String excluirSelecao(@PathVariable("idSelecao") Integer idSelecao, RedirectAttributes redirect) {
		Selecao selecao = this.selecaoService.getSelecaoPorId(idSelecao);
		if (selecao != null && inscricaoRepository.countBySelecao_Id(idSelecao) == 0) {
			this.selecaoService.delete(selecao);
			redirect.addFlashAttribute("info", MENSAGEM_SUCESSO_SELECAO_REMOVIDA);
		} else {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_EXCLUIR_SELECAO_COM_INSCRITOS);
		}

		return REDIRECT_PAGINA_LISTAR_SELECAO;
	}

	@RequestMapping(value = "/comissao/atribuir/{idSelecao}", method = RequestMethod.GET)
	public String atribuirParecerista(@PathVariable("idSelecao") Integer idSelecao,
			Model model, RedirectAttributes redirectAttributes) {

		model.addAttribute("idSelecao", idSelecao);
		model.addAttribute("servidores", servidorService.listarServidores());
		model.addAttribute("selecao", selecaoService.getSelecaoPorId(idSelecao));

		return PAGINA_ATRIBUIR_COMISSAO;
	}

	@RequestMapping(value = "/comissao/atribuir", method = RequestMethod.POST)
	public String atribuirPareceristaNoProjeto(@RequestParam("idSelecao") Integer idSelecao,
			@RequestParam("idServidor") Integer idServidor, Model model, RedirectAttributes redirect) {

		Selecao selecao = selecaoService.getSelecaoPorId(idSelecao);
		List<Servidor> comissao = selecao.getMembrosComissao();
		Servidor servidor = this.servidorService.getServidorPorId(idServidor);

		if (comissao.contains(servidor)) {
			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_MEMBRO_COMISSAO_REPETICAO);
			return REDIRECT_PAGINA_ATRIBUIR_COMISSAO + idSelecao;

		} else {

			selecao.getMembrosComissao().add(servidor);
			selecaoService.update(selecao);
			redirect.addFlashAttribute("info", MENSAGEM_SUCESSO_COMISSAO_FORMADA);

			return REDIRECT_PAGINA_ATRIBUIR_COMISSAO + idSelecao;	
		}
	}
	
	@RequestMapping(value = "/selecao/adicionar-documento/{idSelecao}", method = RequestMethod.GET)
	public String adicionarDocumento(@PathVariable("idSelecao") Integer idSelecao,
			Model model, RedirectAttributes redirectAttributes) {
		
		Selecao selecao = selecaoService.getSelecaoPorId(idSelecao);
		if (selecao != null) {
			model.addAttribute("selecao", selecao);
		}

		return PAGINA_ADICIONAR_ARQUIVO;
	}
	

	@RequestMapping(value = "/selecao/adicionar-documento", method = RequestMethod.POST)
	public String adicionarDocumento(@RequestParam("files") List<MultipartFile> files,
			@RequestParam("idSelecao") Integer idSelecao, Model model, RedirectAttributes redirect) {

		if (files != null && !files.isEmpty() && files.get(0).getSize() > 0) { 
			for (MultipartFile mfiles : files) {
				try {

					if (mfiles.getBytes() != null && mfiles.getBytes().length != 0) {

						Documento documento = new Documento();
						documento.setArquivo(mfiles.getBytes());
						documento.setNome(mfiles.getOriginalFilename());
						documento.setTipo(mfiles.getContentType());
						
						documentoRepository.save(documento);
						
						Selecao selecao = selecaoService.getSelecaoPorId(idSelecao);
						selecao.getDocumentos().add(documento);
						
						selecaoService.save(selecao);
					}


				} catch (IOException ioe) {
					redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_SALVAR_DOCUMENTOS);

					return REDIRECT_PAGINA_ADICIONAR_ARQUIVO +idSelecao;
				}
			} 

		} 

		return REDIRECT_PAGINA_ADICIONAR_ARQUIVO + idSelecao;
	}

	@RequestMapping(value = "/selecao/excluir-documento/{idDocumento}", method = RequestMethod.GET)
	public String excluirDocumento(@PathVariable("idDocumento"
			+ "") Integer idDocumento, @ModelAttribute("selecao") Selecao selecao, 
			Model model, RedirectAttributes redirect) {

		Documento documento = documentoRepository.findById(idDocumento);

		if (documento != null) {
			documentoRepository.delete(documento);

			model.addAttribute("tipoBolsa", TipoSelecao.values());
			model.addAttribute("selecao", selecao);

			return  REDIRECT_PAGINA_ADICIONAR_ARQUIVO + selecao.getId();

		} else {

			model.addAttribute("tipoBolsa", TipoSelecao.values());
			model.addAttribute("selecao", selecao);
			model.addAttribute("anexoError", MENSAGEM_ERRO_ANEXO);

			return REDIRECT_PAGINA_ADICIONAR_ARQUIVO;
		}
	}

	@RequestMapping(value = "/comissao/excluir/{idSelecao}/{idServidor}", method = RequestMethod.GET)
	public String excluirMembroComissao(@PathVariable("idSelecao") Integer idSelecao,@PathVariable("idServidor") Integer idServidor, 
			Model model, Authentication auth, RedirectAttributes redirect) {	

		Selecao selecao = selecaoService.getSelecaoPorId(idSelecao);
		Servidor coordenador = servidorService.getServidorPorCpf(auth.getName());			
		Servidor servidor = this.servidorService.getServidorPorId(idServidor);

		if(coordenador.getId() != servidor.getId()){

			selecao.getMembrosComissao().remove(servidor);
			selecaoService.update(selecao);
			redirect.addFlashAttribute("info", MENSAGEM_SUCESSO_MEMBRO_EXCLUIDO);
		}else

			redirect.addFlashAttribute(ERRO, MENSAGEM_ERRO_COMISSAO_EXCLUIR_COORDENADOR);

		return REDIRECT_PAGINA_ATRIBUIR_COMISSAO + idSelecao;

	}
	
	@RequestMapping(value = "/comissao/relatorioFinal/{idSelecao}")
	public String getInformacoesRelatorioFinal(@PathVariable("idSelecao") Integer idSelecao, Model modelo){
		
		Selecao selecao = selecaoService.getSelecaoPorId(idSelecao);
		
		//dividi o resultado já em 3 listas a serem exibidas na jsp
		
		List<Inscricao> classificados = inscricaoService.getClassificadosPorSelecao(selecao);
		List<Inscricao> reservas = inscricaoService.getClassificaveisPorSelecao(selecao);
		List<Inscricao> indeferidos = inscricaoService.getIndeferidosPorSelecao(selecao);
		
		//ordeno de acordo com o nome dos alunos inscritos
		Collections.sort(classificados);
		Collections.sort(reservas);
		Collections.sort(indeferidos);
		
		modelo.addAttribute("classificados", classificados);
		modelo.addAttribute("reservas", reservas);
		modelo.addAttribute("indeferidos", indeferidos);
		
		modelo.addAttribute(Constants.ABA_SELECIONADA, "classificados-tab");
		
		return PAGINA_RELATORIO_FINAL;
	}

}
