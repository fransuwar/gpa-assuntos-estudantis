package br.ufc.quixada.npi.gpa.service.impl;

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
import br.ufc.quixada.npi.util.SimpleMap;

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

	@Transactional(readOnly = true)
	public List<HorarioDisponivel> getHorariosDisponiveisIniciacaoAcademica(Integer idIniciacaoAcademica) {
		return inscricaoService.find("HorarioDisponivel.findHorarioDisponivelByIdIniciacaoAcademica",
				new SimpleMap<String, Object>("idIniciacaoAcademica", idIniciacaoAcademica));
	}

	@Transactional(readOnly = true)
	public void salvarEntrevista(Entrevista entrevista) {
		entrevistaService.save(entrevista);
	}

	@Override
	public ComQuemMora getComQuemMora(GrauParentesco comQuemMora) {
		return (ComQuemMora) inscricaoService.findFirst("ComQuemMora.findComQuemMoraByDescricao",
				new SimpleMap<String, Object>("descricao", comQuemMora));
	}

	
	@Override
	public PessoaFamilia buscarPessoaFamiliaPorId(Integer idPessoa) {
		Map<String, Object> params = new SimpleMap<String, Object>();
		params.put("idPessoa", idPessoa);
		return (PessoaFamilia) pessoaRepository.findFirst(QueryType.JPQL,"select p from PessoaFamilia as p where p.id = :idPessoa",
				new SimpleMap<String,Object>("idPessoa", idPessoa));

	}

	@Override
	@Transactional
	public void update(Integer idInscricao,boolean classificado) {
		Map<String, Object> params = new SimpleMap<String, Object>();
		params.put("classificado", classificado);
		params.put("idInscricao", idInscricao);
		inscricaoRepository.executeUpdate("update Inscricao set classificado =:classificado where Inscricao.id =:idInscricao", params);
		
	}

	@Override
	@Transactional
	public void consolidacaoDeTodos(Integer idSelecao, boolean consolidacao) {
		Map<String, Object> params = new SimpleMap<String, Object>();
		params.put("consolidacao", consolidacao);
		params.put("idSelecao", idSelecao);
		inscricaoRepository.executeUpdate("update Inscricao set consolidacao =:consolidacao where Inscricao.selecao_id =:idSelecao", params);
		
		
	}

	@Override
	@Transactional
	public void consolidar(Integer idInscricao, boolean consolidacao) {
		Map<String, Object> params = new SimpleMap<String, Object>();
		params.put("consolidacao", consolidacao);
		params.put("idInscricao", idInscricao);
		inscricaoRepository.executeUpdate("update Inscricao set consolidacao =:consolidacao where Inscricao.id =:idInscricao", params);
			
	}

	
}
