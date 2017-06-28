package br.ufc.npi.auxilio.service.impl;

import br.ufc.npi.auxilio.enums.Resultado;
import br.ufc.npi.auxilio.excecao.AuxilioMoradiaException;
import br.ufc.npi.auxilio.model.Aluno;
import br.ufc.npi.auxilio.model.Inscricao;
import br.ufc.npi.auxilio.model.PessoaFamilia;
import br.ufc.npi.auxilio.model.Selecao;
import br.ufc.npi.auxilio.model.questionario.Identificacao;
import br.ufc.npi.auxilio.repository.InscricaoRepository;
import br.ufc.npi.auxilio.repository.PessoaFamiliaRepository;
import br.ufc.npi.auxilio.service.InscricaoService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.time.LocalDateTime;
import java.util.List;

import static br.ufc.npi.auxilio.utils.ErrorMessageConstants.*;

@Named
public class InscricaoServiceImpl implements InscricaoService {
	
	@Autowired
	private InscricaoRepository inscricaoRepository;

	@Autowired
	private PessoaFamiliaRepository pessoaFamiliaRepository;
	
	@Override
	public boolean isInscrito(Aluno aluno, Selecao selecao) {
		return inscricaoRepository.findInscricaoBySelecaoAndAluno(selecao, aluno) != null;
	}
	
	@Override
	public Inscricao salvar(Selecao selecao, Aluno aluno, Identificacao identificacao) throws AuxilioMoradiaException{
		if (aluno == null || selecao == null) {
			throw new AuxilioMoradiaException(MENSAGEM_ERRO_REALIZAR_INSCRICAO);

		} else if(!selecao.isInscricaoAberta()) {
			throw new AuxilioMoradiaException(MENSAGEM_ERRO_INSCRICAO_FORA_DO_PRAZO);

		} else if(isInscrito(aluno, selecao)) {
			throw new AuxilioMoradiaException(MENSAGEM_ERRO_INSCRICAO_EXISTENTE);
		}
		// Cria uma nova inscrição
		Inscricao inscricao = new Inscricao();
		inscricao.setSelecao(selecao);
		inscricao.setAluno(aluno);
		inscricao.setData(LocalDateTime.now());
		inscricao.setSelecionado(0);
		inscricaoRepository.save(inscricao);
		inscricao.setIdentificacao(identificacao);
		return inscricaoRepository.save(inscricao);
	}

	@Override
	public Inscricao get(Aluno aluno, Selecao selecao) {
		return inscricaoRepository.findInscricaoBySelecaoAndAluno(selecao, aluno);
	}

	@Override
	public Inscricao salvar(Inscricao inscricao) {
		inscricao.verificarResultado();
		return inscricaoRepository.save(inscricao);
	}

	@Override
	public Inscricao atualizar(Inscricao inscricao) throws AuxilioMoradiaException {
		if (inscricao == null) {
			throw new AuxilioMoradiaException(MENSAGEM_ERRO_ATUALIZAR_INSCRICAO);
		} else if(!inscricao.getSelecao().isInscricaoAberta()) {
			throw new AuxilioMoradiaException(MENSAGEM_ERRO_INSCRICAO_FORA_DO_PRAZO);
		}
		return inscricaoRepository.save(inscricao);
	}
	
	@Override
	public boolean editar(Inscricao inscricao) {
		Inscricao inscricaoAntigo = this.buscarInscricaoPorId(inscricao.getId());
		if(inscricaoAntigo == null){
			return false;
		}
		try {
			inscricao.setSelecionado(inscricaoAntigo.getSelecionado());
			
			inscricaoRepository.save(inscricao);
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}

	@Override
	public PessoaFamilia adicionarMembroFamilia(PessoaFamilia pessoa) {
		return pessoaFamiliaRepository.save(pessoa);
	}

	@Override
	public void removerMembroFamilia(PessoaFamilia pessoa) {
		pessoaFamiliaRepository.delete(pessoa);
	}

	@Override
	public List<Inscricao> getAll(Aluno aluno) {
		return inscricaoRepository.findInscricaoByAluno(aluno);
	}

	@Override
	public List<Inscricao> inscricoesParaEntrevista(Selecao selecao) {
		return inscricaoRepository.findInscricaoBySelecaoAndEntrevistaAgendadaAndAnaliseDocumentacao_Resultado(selecao, 0, Resultado.DEFERIDO);
	}

	@Override
	public List<Inscricao> getAllOrdenado(Selecao selecao) {
		return inscricaoRepository.findAllBySelecao(selecao);
	}
	
	@Override
	public Inscricao buscarInscricaoPorId(Integer idInscricao){
		return inscricaoRepository.findInscricaoById(idInscricao);
	}
	
	@Override
	public boolean selecionarInscricao(Integer idInscricao,Integer selecionar) {
		Inscricao inscricao = this.buscarInscricaoPorId(idInscricao);
		if(inscricao.getResultado() == Resultado.DEFERIDO){
			inscricao.setSelecionado(selecionar);
			return editar(inscricao);
		}
		return false;
	}

	public List<Inscricao> getIndeferidos(Selecao selecao){
		return inscricaoRepository.getIndeferidos(selecao);
	}
	
	public List<Inscricao> getSelecionados(Selecao selecao){
		return inscricaoRepository.getSelecionados(selecao);

	}
}