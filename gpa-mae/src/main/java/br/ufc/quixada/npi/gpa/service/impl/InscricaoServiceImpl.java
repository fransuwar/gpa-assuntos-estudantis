
package br.ufc.quixada.npi.gpa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.gpa.enums.GrauParentesco;
import br.ufc.quixada.npi.gpa.model.ComQuemMora;
import br.ufc.quixada.npi.gpa.model.Entrevista;
import br.ufc.quixada.npi.gpa.model.HorarioDisponivel;
import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.model.PessoaFamilia;
import br.ufc.quixada.npi.gpa.service.InscricaoService;
import br.ufc.quixada.npi.repository.GenericRepository;


@SuppressWarnings("unchecked")
@Named
public class InscricaoServiceImpl implements InscricaoService {

	@Inject
	private GenericRepository<Entrevista> entrevistaService;
	
	@Inject
	private GenericRepository<PessoaFamilia> pessoaRepository;

	@Inject
	private GenericRepository<Inscricao> inscricaoService;
	
    @Inject
	private GenericRepository<Inscricao> inscricaoRepository;
    

	
	@Override
	public PessoaFamilia buscarPessoaFamiliaPorId(Integer idPessoa) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idPessoa", idPessoa);
		return (PessoaFamilia) pessoaRepository.findFirst(QueryType.JPQL,"select p from PessoaFamilia as p where p.id = :idPessoa",
				params,-1);

	}

	@Override
	@Transactional
	public void update(Integer idInscricao,boolean classificado) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("classificado", classificado);
		params.put("idInscricao", idInscricao);
		inscricaoRepository.find(QueryType.JPQL, "update Inscricao set classificado =:classificado where id =:idInscricao", params);
		
	}

	@Override
	@Transactional
	public void consolidacaoDeTodos(Integer idSelecao, boolean consolidacao) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("consolidacao", consolidacao);
		params.put("idSelecao", idSelecao);
		inscricaoRepository.find(QueryType.JPQL, "update Inscricao set consolidacao =:consolidacao where selecao.id =:idSelecao", params);
		
		
	}

	@Override
	@Transactional
	public void consolidar(Integer idInscricao, boolean consolidacao) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("consolidacao", consolidacao);
		params.put("idInscricao", idInscricao);
		inscricaoRepository.find("QueryType.JPQL, update Inscricao set consolidacao =:consolidacao where id =:idInscricao", params);
			
	}

	
}