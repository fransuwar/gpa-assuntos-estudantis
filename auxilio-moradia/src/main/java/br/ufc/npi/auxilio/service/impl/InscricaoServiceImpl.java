package br.ufc.npi.auxilio.service.impl;

import javax.inject.Named;

import br.ufc.npi.auxilio.model.PessoaFamilia;
import br.ufc.npi.auxilio.service.InscricaoService;

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