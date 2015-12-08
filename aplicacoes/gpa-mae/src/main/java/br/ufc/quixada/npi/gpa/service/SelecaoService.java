package br.ufc.quixada.npi.gpa.service;

import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.service.GenericService;

public interface SelecaoService extends GenericService<Selecao> {
	
	public abstract boolean isSelecaoCadastrada(Selecao selecaoBolsa);

	public abstract Selecao getSelecaoBolsaComDocumentos(Integer IdSelecao);

}
