package br.ufc.quixada.npi.gpa.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.gpa.enums.MoraCom;
import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.model.ComQuemMora;
import br.ufc.quixada.npi.gpa.model.Entrevista;
import br.ufc.quixada.npi.gpa.model.HorarioDisponivel;
import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.model.PessoaFamilia;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.model.Servidor;
import br.ufc.quixada.npi.gpa.model.VisitaDomiciliar;
import br.ufc.quixada.npi.gpa.service.InscricaoService;
import br.ufc.quixada.npi.repository.GenericRepository;
import br.ufc.quixada.npi.service.impl.GenericServiceImpl;
import br.ufc.quixada.npi.util.SimpleMap;

@SuppressWarnings("unchecked")
@Named
public class InscricaoServiceImpl extends GenericServiceImpl<Inscricao> implements InscricaoService {

	@Inject
	private GenericRepository<VisitaDomiciliar> visitaService;

	@Inject
	private GenericRepository<Entrevista> entrevistaService;

	@Override
	@Transactional(readOnly = true)
	public List<Inscricao> getInscricoes(Integer idAluno) {
		return find("Inscricao.findIncricoesByIdAluno", new SimpleMap<String, Object>("idAluno", idAluno));
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Inscricao> getInscricoesBySelecao(Integer idSelecao) {
		return find("Inscricao.finInscricaoByIdSelecao", new SimpleMap<String, Object>("idSelecao", idSelecao));
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Inscricao> getInscricoesBySelecaoByAluno(Integer idSelecao, Integer idAluno) {
		Map<String, Object> params = new SimpleMap<String, Object>();
		params.put("idSelecao", idSelecao);
		params.put("idAluno", idAluno);

		return (List<Inscricao>)find("Inscricao.finInscricaoByIdSelecaoByAluno", params);
	}
	

	@Transactional(readOnly = true)
	public List<HorarioDisponivel> getHorariosDisponiveisIniciacaoAcademica(Integer idIniciacaoAcademica) {
		return find("HorarioDisponivel.findHorarioDisponivelByIdIniciacaoAcademica",
				new SimpleMap<String, Object>("idIniciacaoAcademica", idIniciacaoAcademica));
	}

	@Override
	@Transactional(readOnly = true)
	public List<PessoaFamilia> getPessoaFamiliaByIdIniciacaoAcademica(Integer idIniciacaoAcademica) {
		return find("PessoaFamilia.findPessoaFamiliaByIdIniciacaoAcademica",
				new SimpleMap<String, Object>("idIniciacaoAcademica", idIniciacaoAcademica));
	}

	@Override
	@Transactional(readOnly = true)
	public List<PessoaFamilia> getPessoaFamiliaByIdAuxilioMoradia(Integer idAuxilioMoradia) {
		return find("PessoaFamilia.findPessoaFamiliaByIdAuxilioMoradia",
				new SimpleMap<String, Object>("idAuxilioMoradia", idAuxilioMoradia));
	}

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
	public ComQuemMora getComQuemMora(MoraCom comQuemMora) {
		return (ComQuemMora) findFirst("ComQuemMora.findComQuemMoraByDescricao",
				new SimpleMap<String, Object>("descricao", comQuemMora));
	}

	@Override
	@Transactional(readOnly = true)
	public Inscricao getInscricao(Selecao selecao, Aluno aluno) {
		Map<String, Object> params = new SimpleMap<String, Object>();
		params.put("idSelecao", selecao.getId());
		params.put("idAluno", aluno.getId());
		return (Inscricao) findFirst("Inscricao.findInscricaoAluno", params);

	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Inscricao> getDeferidosBySelecao(Selecao selecao) {
		List<Inscricao> inscricoes = find(QueryType.JPQL, "select i from Inscricao as i where i.selecao.id =:idSelecao and i.deferimento = 'true'",
				new SimpleMap<String,Object>("idSelecao", selecao.getId()));

		return inscricoes;
	}
}
