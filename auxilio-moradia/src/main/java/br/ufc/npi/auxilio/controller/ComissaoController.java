package br.ufc.npi.auxilio.controller;

import br.ufc.npi.auxilio.model.Selecao;
import br.ufc.npi.auxilio.model.Servidor;
import br.ufc.npi.auxilio.service.ServidorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("comissao")
public class ComissaoController {

    @Autowired
    private ServidorService servidorService;

    @GetMapping("/adicionar")
    public String adicionarMembroForm(Model model) {
        model.addAttribute("servidores", servidorService.getAll());
        return "";
    }

    @PostMapping("/adicionar")
    public String adicionarMembro(Selecao selecao, Servidor servidor) {

        return "";
    }

}
