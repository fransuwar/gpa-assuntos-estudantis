package br.ufc.quixada.npi.gpa.service;

import java.util.List;

import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.service.GenericService;

public interface SelecaoService extends GenericService<Selecao> {

	public abstract boolean isSelecaoCadastrada(Selecao selecao);

	public abstract Selecao getSelecaoComDocumentos(Integer idSelecao);

	public abstract List<Selecao> getSelecoesComMembros();

}
