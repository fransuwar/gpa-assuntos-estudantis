package br.ufc.quixada.npi.gpa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.model.Servidor;
import br.ufc.quixada.npi.gpa.repository.SelecaoRepository;
import br.ufc.quixada.npi.gpa.repository.ServidorRepository;
import br.ufc.quixada.npi.gpa.service.SelecaoService;

@Service
public class SelecaoServiceImpl implements SelecaoService {
	
	@Autowired
	private SelecaoRepository selecaoRepository;
	
	@Autowired
	private ServidorRepository servidorRepository;
	
	@Override
	public List<Selecao> getByMembroComissao(String cpf) {
		Servidor servidor = servidorRepository.findByPessoaCpf(cpf);
		return selecaoRepository.findByComissaoIn(servidor);
	}
	
	@Override
	public void cadastrar(Selecao selecao) {
		selecao.setSequencial(selecaoRepository.getNextSequencial(selecao.getAno()));
		selecao.addMembroComissao(selecao.getResponsavel());
		selecaoRepository.save(selecao);
	}

	@Override
	public Selecao getById(Integer id) {
		return selecaoRepository.findOne(id);
	}
	
}
