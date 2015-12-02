package br.ufc.quixada.npi.gpa.service.impl;

import java.util.List;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Named;
import br.ufc.quixada.npi.enumeration.QueryType;
import org.springframework.transaction.annotation.Transactional;
import br.ufc.quixada.npi.gpa.model.HorarioDisponivel;
import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.model.PessoaFamilia;
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
	
	@Override
	public List<Inscricao> listarInscricoesByIdAluno(Integer id) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return find(QueryType.JPQL,"from Inscricao where aluno.id = :id",params);
	}
	
	@Transactional(readOnly = true)
	public List<HorarioDisponivel> getHorariosDisponiveisIniciacaoAcademica(Integer idIniciacaoAcademica) {
		return find("HorarioDisponivel.findHorarioDisponivelByIdQuest", new SimpleMap<String, Object>("idIniciacaoAcademica", idIniciacaoAcademica));
	}

	@Override
	@Transactional(readOnly = true)
	public List<PessoaFamilia> getPessoaFamiliaByIdIniciacaoAcademica(Integer idIniciacaoAcademica) {
		return find("PessoaFamilia.findPessoaFamiliaByIdQuestBIA", 
					new SimpleMap<String, Object>("idIniciacaoAcademica", idIniciacaoAcademica));
	}

	@Override
	@Transactional(readOnly = true)
	public List<PessoaFamilia> getPessoaFamiliaByIdAuxilioMoradia(Integer idAuxilioMoradia) {
		return find("PessoaFamilia.findPessoaFamiliaByIdQuestAMOR", 
				new SimpleMap<String, Object>("idAuxilioMoradia", idAuxilioMoradia));
	}

	@Override
	public void salvarVisitaDocimiciliar(VisitaDomiciliar visitaDocimiciliar) {
		visitaService.save(visitaDocimiciliar);
		
	}

	@Override
	public void atualizarVisitaDomiciliar(VisitaDomiciliar visitaDocimiciliar) {
		visitaService.update(visitaDocimiciliar);
		
	}

	@Override
	@Transactional(readOnly = true)
	public VisitaDomiciliar getVisitaDocimiciliarByIdVisitaDomiciliar(Integer idVisitaDomiciliar) {
		return visitaService.find(VisitaDomiciliar.class, idVisitaDomiciliar);
	}

}
