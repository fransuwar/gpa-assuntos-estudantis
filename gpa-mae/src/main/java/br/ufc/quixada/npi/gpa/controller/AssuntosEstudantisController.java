package br.ufc.quixada.npi.gpa.controller;

import java.util.Set;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.quixada.npi.gpa.utils.Constants;

@Controller
public class AssuntosEstudantisController {
	
	@GetMapping({"", "/"})
	public ModelAndView getIndex() {
		Set<String> roles = AuthorityUtils
                .authorityListToSet(SecurityContextHolder.getContext()
                        .getAuthentication().getAuthorities());
        if (roles.contains("STA") || roles.contains("DOCENTE")) {
            return new ModelAndView(Constants.REDIRECT_PAGINA_LISTAR_SELECAO_SERVIDOR);
        }
        return new ModelAndView(Constants.REDIRECT_PAGINA_ALUNO_LISTAR_SELECAO);
	}

}
