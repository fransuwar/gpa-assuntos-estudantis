package br.ufc.quixada.npi.gpa.service;

import java.util.List;

import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.service.GenericService;

public interface SelecaoService extends GenericService<Selecao> {

	public abstract boolean isSelecaoCadastrada(Selecao selecao);

	public abstract Selecao getSelecaoComDocumentos(Integer id);

	public abstract List<Selecao> getSelecoesComMembros();

	public abstract Selecao getSelecaoComMembros(Integer id);

	public abstract Selecao getSelecaoComAlunos(Integer id);
	
	public abstract List<Selecao> getSelecoesComAlunos();


}
