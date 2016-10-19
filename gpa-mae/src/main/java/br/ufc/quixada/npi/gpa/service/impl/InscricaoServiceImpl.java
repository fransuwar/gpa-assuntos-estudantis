package br.ufc.quixada.npi.gpa.service.impl;

import javax.inject.Named;

import br.ufc.quixada.npi.gpa.model.PessoaFamilia;
import br.ufc.quixada.npi.gpa.service.InscricaoService;

@Named
public class InscricaoServiceImpl implements InscricaoService {

	@Override
	public PessoaFamilia buscarPessoaFamiliaPorId(Integer idPessoa) {
		/*Map<String, Object> params = new HashMap<String, Object>();
		params.put("idPessoa", idPessoa);
		return (PessoaFamilia) pessoaRepository.findFirst(QueryType.JPQL,"select p from PessoaFamilia as p where p.id = :idPessoa",
				params,-1);*/
		return null;

	}
	
}