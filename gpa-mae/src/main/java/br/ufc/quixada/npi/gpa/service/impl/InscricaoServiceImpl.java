
package br.ufc.quixada.npi.gpa.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.gpa.model.PessoaFamilia;
import br.ufc.quixada.npi.gpa.service.InscricaoService;
import br.ufc.quixada.npi.repository.GenericRepository;


@Named
public class InscricaoServiceImpl implements InscricaoService {

	@Inject
	private GenericRepository<PessoaFamilia> pessoaRepository;
	
	@Override
	public PessoaFamilia buscarPessoaFamiliaPorId(Integer idPessoa) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idPessoa", idPessoa);
		return (PessoaFamilia) pessoaRepository.findFirst(QueryType.JPQL,"select p from PessoaFamilia as p where p.id = :idPessoa",
				params,-1);

	}
	
}