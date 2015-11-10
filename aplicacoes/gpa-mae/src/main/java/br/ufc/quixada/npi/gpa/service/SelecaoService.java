package br.ufc.quixada.npi.gpa.service;

import java.util.List;

import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.service.GenericService;

public interface SelecaoService extends GenericService<Selecao> {

	public abstract List<Selecao> getSelecaoBolsasSubmetidos();
	
	public List<Selecao> getSelecaoBolsasAtribuidos();

	public abstract List<Selecao> getSelecaoBolsasByUsuario(Integer id);
	
	public abstract List<Selecao> getSelecaoBolsasAguardandoParecer();
	
	public abstract boolean existsSelecaoEquals(Selecao selecaoBolsa);

	public abstract Selecao getSelecaoBolsaComDocumentos(Integer id);

	public abstract List<Selecao> getSelecaoBolsaComMembros();

	public abstract Selecao getSelecaoBolsaComMembros(Integer id);

	public abstract Selecao getSelecaoBolsaComAlunos(Integer id);
	
	public abstract List<Selecao> getSelecaoComAlunos();

}
