package br.com.ufc.quixada.npi.gpa.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import br.com.ufc.quixada.npi.gpa.model.SelecaoBolsa;
import br.com.ufc.quixada.npi.gpa.service.SelecaoBolsaService;



//import java.io.*;




@Controller
@RequestMapping("selecaoBolsa")
public class SelecaoBolsaController {
	@Inject
	private SelecaoBolsaService serviceSelecaoBolsa;
	
	
	@RequestMapping(value="/index" , method = RequestMethod.GET )
	public String index(){
		return "/selecaoBolsa/listar";
		
	}
	
	@RequestMapping(value="/cadastrarBolsa", method = RequestMethod.GET)
	 public String cadastro(Model modelo){
		modelo.addAttribute("SelecaoBolsa", new SelecaoBolsa());
		//modelo.addAttribute("tipoBolsa", tipoBolsa.values());
		return "selecaoBolsa/cadastrarBolsa";
	}	
	
	
	@RequestMapping(value="/cadastrarBolsa" , method = RequestMethod.POST)
	  public String adicionaSelecao( @Valid @ModelAttribute("selecaoBolsa") SelecaoBolsa selecaoBolsa , BindingResult result , HttpSession session, RedirectAttributes redirect  ){
		if(result.hasErrors()){
			return "selecaoBolsa/cadastrarBolsa";
		}
		
		//selecaoBolsa.setStatusSelecao(StatusSelecaoBolsa.NOVO);
		
		this.serviceSelecaoBolsa.save(selecaoBolsa);
		redirect.addFlashAttribute("inFo", "Cadastrado Com sucesso");
		
		
		return "redirect:/selecaoBolsa/index";
		
	}	
	
		
	
	

}
	
	
	
	
	

	
	
	

	
	 
	
	
	

