package br.ufc.quixada.npi.gpa.service;



import br.ufc.quixada.npi.gpa.model.Entrevista;
import br.ufc.quixada.npi.gpa.model.Inscricao;

public interface InscricaoService extends GenericService<Inscricao>{
	
	public abstract Inscricao getInscricaoId(Integer id);
	
	public abstract void saveEntrevista(Entrevista entrevista);
}
