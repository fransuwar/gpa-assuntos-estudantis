package br.ufc.quixada.npi.gpa.service.impl;

import javax.inject.Named;

import br.ufc.quixada.npi.gpa.model.Pessoa;
import br.ufc.quixada.npi.gpa.service.PessoaService;
import br.ufc.quixada.npi.service.impl.GenericServiceImpl;
import br.ufc.quixada.npi.util.SimpleMap;

@Named
public class PessoaServiceImpl extends GenericServiceImpl<Pessoa> implements PessoaService {
	
	@Override
	public Pessoa getPessoaPorCpf(String cpf) {
		return (Pessoa) findFirst("Pessoa.findPessoaByCpf", new SimpleMap<String, Object>("cpf", cpf));
		}
	
}
