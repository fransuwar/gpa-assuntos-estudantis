package br.ufc.quixada.npi.gpa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.gpa.enums.GrauParentesco;
import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.model.ComQuemMora;
import br.ufc.quixada.npi.gpa.model.Entrevista;
import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.model.PessoaFamilia;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.model.VisitaDomiciliar;
import br.ufc.quixada.npi.gpa.service.InscricaoService;
import br.ufc.quixada.npi.repository.GenericRepository;

@SuppressWarnings("unchecked")
@Named
public class InscricaoServiceImpl implements InscricaoService {

	@Inject
	private GenericRepository<VisitaDomiciliar> visitaService;

	@Inject
	private GenericRepository<Entrevista> entrevistaService;
	
	@Inject
	private GenericRepository<PessoaFamilia> pessoaRepository;

	@Inject
	private GenericRepository<Inscricao> inscricaoService;
	
    @Inject
	private GenericRepository<Inscricao> inscricaoRepository;
    
    @Inject
    private GenericRepository<ComQuemMora> comQuemMoraRepository;
    
	@Override
	@Transactional(readOnly = true)
	public List<Inscricao> getInscricoesPorSelecao(Integer idSelecao) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idSelecao", idSelecao);
		return inscricaoRepository.find("Inscricao.finInscricaoByIdSelecao", map);

	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Inscricao> getInscricoesPorSelecaoPorAluno(Integer idSelecao, Integer idAluno) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("idSelecao", idSelecao);
		params.put("idAluno", idAluno);

		return (List<Inscricao>)inscricaoService.find("Inscricao.finInscricaoByIdSelecaoByAluno", params);
	}

	/*@Override
	@Transactional(readOnly = true)
	public List<PessoaFamilia> getPessoaFamiliaPorIdIniciacaoAcademica(Integer idIniciacaoAcademica) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idIniciacaoAcademica", idIniciacaoAcademica);
		return pessoaRepository.find("PessoaFamilia.findPessoaFamiliaByIdIniciacaoAcademica",
<<<<<<< HEAD
				params);
	}

=======
				new SimpleMap<String, Object>("idIniciacaoAcademica", idIniciacaoAcademica));
	}*/
/*
>>>>>>> refs/heads/master
	@Override
	@Transactional(readOnly = true)
	public List<PessoaFamilia> getPessoaFamiliaPorIdAuxilioMoradia(Integer idAuxilioMoradia) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idAuxilioMoradia", idAuxilioMoradia);
		return pessoaRepository.find("PessoaFamilia.findPessoaFamiliaByIdAuxilioMoradia",
<<<<<<< HEAD
				params);
	}
=======
				new SimpleMap<String, Object>("idAuxilioMoradia", idAuxilioMoradia));
	}*/


	@Override
	public void salvarVisitaDocimiciliar(VisitaDomiciliar visitaDocimiciliar) {
		visitaService.save(visitaDocimiciliar);
	}


	@Transactional(readOnly = true)
	public void salvarEntrevista(Entrevista entrevista) {
		entrevistaService.save(entrevista);
	}

	@Override
	public void atualizarVisitaDomiciliar(VisitaDomiciliar visitaDocimiciliar) {
		visitaService.update(visitaDocimiciliar);

	}

	@Override
	@Transactional(readOnly = true)
	public VisitaDomiciliar getVisitaDocimiciliar(Integer idVisitaDomiciliar) {
		return visitaService.find(VisitaDomiciliar.class, idVisitaDomiciliar);
	}

	@Override

	public ComQuemMora getComQuemMora(GrauParentesco  comQuemMora) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("descricao", comQuemMora);
		return comQuemMoraRepository.findFirst("ComQuemMora.findComQuemMoraByDescricao", params);

	}

	@Override
	public Inscricao getInscricao(Selecao selecao, Aluno aluno) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idSelecao", selecao.getId());
		params.put("idAluno", aluno.getId());
		return (Inscricao) inscricaoService.findFirst("Inscricao.findInscricaoAluno", params);

	}

	@Override
	public Inscricao getInscricaoPorId(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idInscricao", id);
		return (Inscricao) inscricaoService.findFirst(QueryType.JPQL,"select i from Inscricao as i where i.id = :idInscricao", 
				params,-1);
	}

	@Override
	public void save(Inscricao inscricao) {
		inscricaoService.save(inscricao);
		
	}

	@Override
	public void update(Inscricao inscricao) {
		inscricaoService.update(inscricao);
		
	}

	@Override
	public void delete(Inscricao inscricao) {
		inscricaoService.delete(inscricao);
	}



	public void excluirPessoaFamiliaPorId(Integer idPessoa) {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("idPessoa", idPessoa);

		PessoaFamilia pessoa = (PessoaFamilia) pessoaRepository.findFirst(QueryType.JPQL,"select p from PessoaFamilia as p where p.id = :idPessoa",
				params,-1);
		pessoaRepository.delete(pessoa);

	}


	@Transactional(readOnly = true)
	public List<Inscricao> getDeferidosBySelecao(Selecao selecao) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idSelecao", selecao.getId());
		List<Inscricao> inscricoes = inscricaoRepository.find(QueryType.JPQL, "select i from Inscricao as i where i.selecao.id =:idSelecao and i.deferimento = 'true'",
				params );

		return inscricoes;
	}



	@Override
	public List<Inscricao> getClassificadosPorSelecao(Selecao selecao) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idSelecao", selecao.getId());
		List<Inscricao> inscricoes = inscricaoRepository.find(QueryType.JPQL, "select i from Inscricao as i where i.selecao.id =:idSelecao and i.classificado = 'true'",
				params);

		return inscricoes;
	}
	
	@Override
	public List<Inscricao> getClassificaveisPorSelecao(Selecao selecao) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idSelecao", selecao.getId());
		List<Inscricao> inscricoes = inscricaoRepository.find(QueryType.JPQL, "select i from Inscricao as i where i.selecao.id =:idSelecao and i.classificado = 'false'",
				params);

		return inscricoes;
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
	public List<Inscricao> getIndeferidosPorSelecao(Selecao selecao) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idSelecao", selecao.getId());
		List<Inscricao> inscricoes = inscricaoRepository.find(QueryType.JPQL, "select i from Inscricao as i where i.selecao.id =:idSelecao and i.resultado = 'INDEFERIDO'",
				params);

		return inscricoes;
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

	@Override
	public PessoaFamilia buscarPessoaFamiliaPorId(Integer idPessoa) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
