package br.ufc.quixada.npi.service;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.ufc.quixada.npi.model.Aluno;
import br.ufc.quixada.npi.service.AlunoService;


public class TestaServicos {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		AlunoService as = (AlunoService)ctx.getBean("alunoServiceImpl");
		

		List<Aluno> l = as.findAll();

	    for (Aluno c : l) {
	    	System.out.println(c.getId() + " " + c.getMatricula() );
	    	//if(c.getUsuario() != null){
	    	//	System.out.println( c.getUsuario().getNome());
	    	//}
	    }
	    
		ctx.close();
	}
}
