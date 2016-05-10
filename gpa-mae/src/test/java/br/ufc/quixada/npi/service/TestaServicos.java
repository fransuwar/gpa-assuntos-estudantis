package br.ufc.quixada.npi.service;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.gpa.model.QuestionarioAuxilioMoradia;
import br.ufc.quixada.npi.util.SimpleMap;


public class TestaServicos {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		GenericService<QuestionarioAuxilioMoradia> service = (GenericService<QuestionarioAuxilioMoradia>)ctx.getBean("genericServiceImpl");
		
		QuestionarioAuxilioMoradia quest = (QuestionarioAuxilioMoradia) service.findFirst(QueryType.JPQL,"SELECT DISTINCT am FROM QuestionarioAuxilioMoradia am WHERE am.id = :idQuest",
				new SimpleMap<String, Object>("idQuest", 1));
		
		System.out.println(quest.toString());
		
	    
	    //as.findById(1);
		ctx.close();
	}
}
