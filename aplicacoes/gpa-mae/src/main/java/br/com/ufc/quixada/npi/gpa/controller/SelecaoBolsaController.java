package br.com.ufc.quixada.npi.gpa.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;

import org.joda.time.DateTime;
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

import br.com.ufc.quixada.npi.gpa.enums.Status;
import br.com.ufc.quixada.npi.gpa.enums.TipoBolsa;
import br.com.ufc.quixada.npi.gpa.model.Documento;
import br.com.ufc.quixada.npi.gpa.model.SelecaoBolsa;
import br.com.ufc.quixada.npi.gpa.model.Servidor;
import br.com.ufc.quixada.npi.gpa.service.DocumentoService;
import br.com.ufc.quixada.npi.gpa.service.SelecaoBolsaService;
import br.com.ufc.quixada.npi.gpa.service.ServidorService;

@Named
@RequestMapping("selecao")
public class SelecaoBolsaController {

	@Inject
	private DocumentoService documentoService;
	@Inject
	private ServidorService servidorService;
	@Inject
	private SelecaoBolsaService selecaoService;

	@RequestMapping(value = "{id}/informacoes")
	public String getInformacoes(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		SelecaoBolsa selecao = selecaoService.getSelecaoBolsaComDocumentos(id);
		if (selecao == null) {
			redirectAttributes.addFlashAttribute("erro", "seleção Inexistente");
			return "redirect:/selecao/listar";
		}
		model.addAttribute("selecao", selecao);

		return "selecao/informacoes";
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String salvarSelecaoBolsa(
			@Valid @ModelAttribute(value = "selecao") SelecaoBolsa selecaoBolsa,@RequestParam("files") List<MultipartFile> files,
			BindingResult result, Model model, RedirectAttributes redirect)
			throws IOException {

		// verificar se os documentos foram anexados
		List<Documento> documentos = new ArrayList<Documento>();
		if (files != null && !files.isEmpty()) {
			
			for (MultipartFile mfiles : files) {
				try {
					if (mfiles.getBytes() != null && mfiles.getBytes().length != 0) {
						Documento documento = new Documento();
						documento.setArquivo(mfiles.getBytes());
						documento.setNome(mfiles.getOriginalFilename());
						documento.setTipo(mfiles.getContentType());
						documento.setSelecaoBolsa(selecaoBolsa);
						documentos.add(documento);
					}
				} catch (IOException e) {
					model.addAttribute("erro", "Não foi possivel salvar os documentos.");
					return "selecao/cadastrar";
				}
			}
			
			if(!documentos.isEmpty()){
				selecaoBolsa.setDocumentos(documentos);
			}
			if(selecaoBolsa.getDataInicio().after(selecaoBolsa.getDataTermino())){
				model.addAttribute("dataInicioError", "Data de início deve ser menor que a de termino");
				return ("selecao/cadastrar");  
			}

			if (selecaoBolsa.getId() != null) {
				if (result.hasErrors()) {
					model.addAttribute("action", "editar");
					return "selecao/cadastrar";
				}

				if (selecaoBolsa.getAno() < DateTime.now().getYear()) {
					model.addAttribute("dataError",
							"Digite um ano maior ou igual ao atual");
					return ("selecao/cadastrar");
				}

				this.selecaoService.update(selecaoBolsa);
				redirect.addFlashAttribute("info",
						"Seleção atualizada com sucesso.");
				return "redirect:/selecao/listar";

			} else {
				return adicionarSelecao(selecaoBolsa, result, redirect, model);
			}
		}else{
			redirect.addFlashAttribute("erro",
			"Você não anexou os arquivos.");
			return "selecao/cadastrar";
		}

		
	}

	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
	public String cadastro(Model model) {
		model.addAttribute("action", "cadastrar");
		model.addAttribute("tipoBolsa", TipoBolsa.toMap());
		model.addAttribute("selecao", new SelecaoBolsa());
		return "/selecao/cadastrar";
	}

	public String adicionarSelecao(
			@Valid @ModelAttribute("selecao") SelecaoBolsa selecao,
			BindingResult result, RedirectAttributes redirect, Model model) {

		if (result.hasErrors()) {
			return ("selecao/cadastrar");
		}

		if (selecao.getAno() < DateTime.now().getYear()) {
			model.addAttribute("dataError",
					"Digite um ano maior ou igual ao atual");
			return ("selecao/cadastrar");
		}
		
		if(selecao.getDataInicio().after(selecao.getDataTermino())){
			model.addAttribute("dataInicioError", "Data de início deve ser menor que a de termino");
			return ("selecao/cadastrar");  
		}

		if (selecaoService.existsSelecaoEquals(selecao)) {
			redirect.addFlashAttribute("erro", "Número do edital ou tipo de Bolsa já existente");
			return "redirect:/selecao/listar";
		}
		selecao.setStatus(Status.NOVA);
		this.selecaoService.save(selecao);
		redirect.addFlashAttribute("info", "Seleção realizada com Sucesso.");
		return "redirect:/selecao/listar";
	}

	@RequestMapping(value = "/{id}/editar", method = RequestMethod.GET)
	public String editar(@PathVariable("id") Integer id, RedirectAttributes redirect, Model model) {
		SelecaoBolsa selecao = selecaoService.find(SelecaoBolsa.class, id);

		if (selecao.getStatus()!=null && selecao.getStatus().equals(Status.NOVA)) {

			model.addAttribute("tipoBolsa", TipoBolsa.toMap());
			model.addAttribute("selecao", selecao);
			model.addAttribute("action", "editar");

		} else {
			redirect.addFlashAttribute("erro", "Permissão negada.");
			return "redirect:/selecao/listar";
		}
		return "selecao/cadastrar";
	}

	@RequestMapping(value = "/{id}/excluir")
	public String excluirSelecao(SelecaoBolsa p,
			@PathVariable("id") Integer id,
			RedirectAttributes redirectAttributes) {
		SelecaoBolsa selecao = selecaoService.find(SelecaoBolsa.class, id);
		if (selecao == null) {
			redirectAttributes
					.addFlashAttribute("erro", "Seleção inexistente.");
			return "redirect:/selecao/listar";
		}
		if (selecao.getStatus().equals(Status.NOVA)) {
			this.selecaoService.delete(selecao);
			redirectAttributes.addFlashAttribute("info",
					"Seleção excluída com sucesso.");
		} else {
			redirectAttributes.addFlashAttribute("erro", "Permissão negada.");
		}
		return "redirect:/selecao/listar";
	}

	@RequestMapping(value = "/listar")
	public String listar(ModelMap model) {

		selecaoService.atualizaStatusSelecaoBolsa();
		
		model.addAttribute("selecoes", this.selecaoService.find(SelecaoBolsa.class));
		model.addAttribute("inic_acad", TipoBolsa.INIC_ACAD);
		model.addAttribute("aux_mor", TipoBolsa.AUX_MOR);
		
		return "selecao/listar";
	}

	@RequestMapping(value = "/{id}/atribuir", method = RequestMethod.GET)
	public String atribuirParecerista(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {

		model.addAttribute("selecao", id);
		model.addAttribute("servidores", servidorService.find(Servidor.class));
		return "selecao/atribuir";
	}

	@RequestMapping(value = "/atribuir", method = RequestMethod.POST)
	public String atribuirPareceristaNoProjeto(
			@RequestParam("id1") Integer id1, @RequestParam("id2") Integer id2,
			@RequestParam("id3") Integer id3, @RequestParam("id") Integer id,
			RedirectAttributes redirect) {

		if (id1.equals(id2) || id1.equals(id3) || id2.equals(id3)) {
			redirect.addFlashAttribute("erro",
					"Não é permitida repetição de membros na banca.");
			return "redirect:/selecao/" + id + "/atribuir";
		} else {
			SelecaoBolsa selecao = selecaoService.find(SelecaoBolsa.class, id);
			redirect.addFlashAttribute("selecao", id);
			redirect.addFlashAttribute("membrosBanca", (selecao.getId()));

			List<Servidor> list = new ArrayList<Servidor>();
			Servidor servidor = servidorService.find(Servidor.class, id1);
			servidor.getParticipaBancas().add(selecao);
			list.add(servidor);

			servidor = servidorService.find(Servidor.class, id2);
			servidor.getParticipaBancas().add(selecao);
			list.add(servidor);

			servidor = servidorService.find(Servidor.class, id3);
			servidor.getParticipaBancas().add(selecao);
			list.add(servidor);

			selecao.setMembrosBanca(list);

			selecaoService.update(selecao);
			redirect.addFlashAttribute("info",
					"O Membro da banca foi atribuído com sucesso.");

			return "redirect:/selecao/listar";
		}
	}

}
