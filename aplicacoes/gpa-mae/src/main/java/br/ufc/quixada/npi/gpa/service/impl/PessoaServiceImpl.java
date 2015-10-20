package br.ufc.quixada.npi.gpa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.gpa.model.Papel;
import br.ufc.quixada.npi.gpa.model.Pessoa;
import br.ufc.quixada.npi.gpa.service.PessoaService;
import br.ufc.quixada.npi.repository.GenericRepository;
import br.ufc.quixada.npi.service.impl.GenericServiceImpl;
import br.ufc.quixada.npi.util.SimpleMap;

@Named
public class PessoaServiceImpl extends GenericServiceImpl<Pessoa> implements PessoaService {

	@Inject
	private GenericRepository<Papel> papelRepository;
	
	@Override
	public Pessoa getPessoaByLogin(String login) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "login", login);
		Pessoa pessoaLogada = (Pessoa) find(QueryType.JPQL, "from Pessoa where login = :login", params).get(0);
		return pessoaLogada;
	}

	@Override
	public List<Pessoa> getPareceristas(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "id", id);
		List<Pessoa> pessoas = find(QueryType.JPQL, "from Pessoa u where u.id != :id", params);
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

	@Override
	public List<Papel> getPapeis(String cpf) {
		return papelRepository.find(QueryType.JPQL, "SELECT p.papeis FROM Pessoa p WHERE p.cpf = :cpf", new SimpleMap<String, Object>("cpf", cpf));
	}

}
