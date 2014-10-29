package br.com.ufc.quixada.npi.gpa.service;

import java.util.List;

import br.com.ufc.quixada.npi.gpa.model.Pessoa;
import br.com.ufc.quixada.npi.gpa.model.SelecaoBolsa;

public interface PessoaService extends GenericService<Pessoa> {

	public abstract Pessoa getPessoaByLogin(String login);

	public abstract List<Pessoa> getPareceristas(Long id);

	public abstract boolean isCoordenador(Pessoa pessoa);
}
