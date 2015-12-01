package br.ufc.quixada.npi.gpa.service.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.model.HorarioDisponivel;
import br.ufc.quixada.npi.gpa.model.Inscricao;
import br.ufc.quixada.npi.gpa.model.PessoaFamilia;
import br.ufc.quixada.npi.gpa.model.QuestionarioIniciacaoAcademica;
import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.service.InscricaoService;
import br.ufc.quixada.npi.service.impl.GenericServiceImpl;
import br.ufc.quixada.npi.util.SimpleMap;

@SuppressWarnings("unchecked")
@Named
public class InscricaoServiceImpl extends GenericServiceImpl<Inscricao> implements InscricaoService {
	
	@Inject
	private GenericServiceImpl<QuestionarioIniciacaoAcademica> iniciacaoAcademicaService;

	@Override
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
	public void realizarInscricaoIniciacaoAcademica(Selecao selecao, Aluno aluno, QuestionarioIniciacaoAcademica iniciacaoAcademica) {

		Inscricao inscricao = new Inscricao();
		
		inscricao.setData(new Date());
		
		inscricao.setAluno(aluno);
		inscricao.setSelecao(selecao);
		inscricao.setQuestionarioIniciacaoAcademica(iniciacaoAcademica);

		this.save(inscricao);

	}

	@Override
	public void atualizarInscricaoIniciacaoAcademica(QuestionarioIniciacaoAcademica iniciacaoAcademica) {
		iniciacaoAcademicaService.update(iniciacaoAcademica);
		
	}
	
	

}
