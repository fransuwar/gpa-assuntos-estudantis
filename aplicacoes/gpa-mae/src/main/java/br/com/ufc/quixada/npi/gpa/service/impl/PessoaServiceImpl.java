package br.com.ufc.quixada.npi.gpa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.ufc.quixada.npi.gpa.model.Papel;
import br.com.ufc.quixada.npi.gpa.model.Pessoa;
import br.com.ufc.quixada.npi.gpa.repository.PessoaRepository;
import br.com.ufc.quixada.npi.gpa.repository.QueryType;
import br.com.ufc.quixada.npi.gpa.service.PessoaService;

@Named
public class PessoaServiceImpl extends GenericServiceImpl<Pessoa> implements PessoaService {

	@Inject
	PessoaRepository pessoaRepository;
	
	@Override
	public Pessoa getPessoaByLogin(String login) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "login", login);
		Pessoa pessoaLogada = pessoaRepository.find(QueryType.JPQL, "from Pessoa where login = :login", params).get(0);
		return pessoaLogada;
	}

	@Override
	public List<Pessoa> getPareceristas(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "id", id);
		List<Pessoa> pessoas = pessoaRepository.find(QueryType.JPQL, "from Pessoa u where u.id != :id", params);
		return pessoas;
	}

	@Override
	public boolean isCoordenador(Pessoa pessoa) {
		List<Papel> papeis = pessoa.getPapeis();
		for(Papel p: papeis){
			if(p.getNome().equals("ROLE_COORDENADOR")){
				return true;
			}
		}
		return false;
	}

}
