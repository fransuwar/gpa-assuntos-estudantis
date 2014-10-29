package br.ufc.quixada.npi.service;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.ufc.quixada.npi.gpa.model.Aluno;


public class TestaServicos {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		GenericService<Aluno> as = (GenericService<Aluno>)ctx.getBean("genericServiceImpl");
		
		List<Aluno> l = as.find(Aluno.class);

	    for (Aluno c : l) {
	    	System.out.println(c.getId() + " " + c.getMatricula());
	    	
	    }
	    
	    //as.findById(1);
		ctx.close();
	}
}
