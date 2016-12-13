package br.ufc.npi.auxilio.service.impl;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufc.npi.auxilio.model.Aluno;
import br.ufc.npi.auxilio.model.Inscricao;
import br.ufc.npi.auxilio.model.Pessoa;
import br.ufc.npi.auxilio.model.Selecao;
import br.ufc.npi.auxilio.repository.InscricaoRepository;
import br.ufc.npi.auxilio.service.InscricaoService;

@Named
public class InscricaoServiceImpl implements InscricaoService {
	
	@Autowired
	private InscricaoRepository inscricaoRepository;
	
	@Override
	public boolean estaInscrito(Pessoa pessoa, Selecao selecao) {
		return inscricaoRepository.findInscricaoBySelecaoAndAlunoPessoa(selecao, pessoa) != null;
	}
	
	//Remover
	@Override
	public Inscricao salvar(Selecao selecao, Aluno aluno) {
		Inscricao inscricao = new Inscricao();
		inscricao.setSelecao(selecao);
		inscricao.setAluno(aluno);
		return inscricaoRepository.save(inscricao);
	}
	///
	
	@Override
	public Inscricao salvar(Inscricao inscricao) {
		return inscricaoRepository.save(inscricao);
	}
}