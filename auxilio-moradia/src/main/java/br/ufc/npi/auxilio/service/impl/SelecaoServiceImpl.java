package br.ufc.npi.auxilio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.auxilio.model.Selecao;
import br.ufc.npi.auxilio.model.Servidor;
import br.ufc.npi.auxilio.repository.SelecaoRepository;
import br.ufc.npi.auxilio.repository.ServidorRepository;
import br.ufc.npi.auxilio.service.SelecaoService;

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
		selecao.addMembroComissao(selecao.getResponsavel());
		selecaoRepository.save(selecao);
	}

	@Override
	public Selecao getById(Integer id) {
		return selecaoRepository.findOne(id);
	}

	@Override
	public List<Selecao> getAll() {
		return selecaoRepository.findAll();
	}

	@Override
	public void atualizar(Selecao selecao) {
		selecaoRepository.save(selecao);
	}

	@Override
	public void excluir(Selecao selecao) {
		selecaoRepository.delete(selecao);
	}
	
}
