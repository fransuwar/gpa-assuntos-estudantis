package br.ufc.quixada.npi.gpa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.gpa.model.Pessoa;
import br.ufc.quixada.npi.gpa.service.PessoaService;
import br.ufc.quixada.npi.service.impl.GenericServiceImpl;
import br.ufc.quixada.npi.util.SimpleMap;

@Named
public class PessoaServiceImpl extends GenericServiceImpl<Pessoa> implements PessoaService {
	
	@Override
	public Pessoa getPessoaByCpf(String cpf) {
		return (Pessoa) findFirst("Pessoa.findPessoaByCpf", new SimpleMap<String, Object>("cpf", cpf));
		}

	@Override
	public List<Pessoa> getPareceristas(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "id", id);
		@SuppressWarnings("unchecked")
		List<Pessoa> pessoas = find(QueryType.JPQL, "from Pessoa u where u.id != :id", params);
		return pessoas;
	}

}
