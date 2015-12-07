package br.ufc.quixada.npi.gpa.service;

import java.util.List;

import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.service.GenericService;

public interface SelecaoService extends GenericService<Selecao> {

	public abstract List<Selecao> getSelecoesSubmetidos();
	
	public List<Selecao> getSelecoesAtribuidos();

	public abstract List<Selecao> getSelecoesByUsuario(Integer id);
	
	public abstract List<Selecao> getSelecoesAguardandoParecer();
	
	public abstract boolean existsSelecaoEquals(Selecao selecao);

	public abstract Selecao getSelecaoComDocumentos(Integer id);

	public abstract List<Selecao> getSelecoesComMembros();

	public abstract Selecao getSelecaoComMembros(Integer id);

	public abstract Selecao getSelecaoComAlunos(Integer id);
	
	public abstract List<Selecao> getSelecoesComAlunos();

}
