package br.ufc.quixada.npi.gpa.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.gpa.model.Selecao;
import br.ufc.quixada.npi.gpa.model.Servidor;
import br.ufc.quixada.npi.gpa.repository.SelecaoRepository;
import br.ufc.quixada.npi.gpa.repository.ServidorRepository;
import br.ufc.quixada.npi.gpa.service.SelecaoService;

@Named
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
	
	
	// old
	@Override
	@Transactional
	public boolean SelecaoEstaCadastrada(Selecao selecao) {

		/*Map<String, Object> map = new HashMap<String, Object>();
		map.put(ANO, selecao.getAno());
		map.put("sequencial", selecao.getSequencial());
		List<Selecao> selecoes = selecaoService.find(QueryType.JPQL,
				"from Selecao as p where p.tipoSelecao = :tipo and p.ano = :ano and p.sequencial = :sequencial",
				map);

		return !(selecoes == null || selecoes.isEmpty());*/
		return true;
	}
	
}
