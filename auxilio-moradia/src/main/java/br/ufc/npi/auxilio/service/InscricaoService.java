package br.ufc.npi.auxilio.service;

import br.ufc.npi.auxilio.excecao.AuxilioMoradiaException;
import br.ufc.npi.auxilio.model.*;
import br.ufc.npi.auxilio.model.questionario.Identificacao;

import java.util.List;

public interface InscricaoService {
	
	boolean isInscrito(Aluno aluno, Selecao selecao);
	
	Inscricao salvar(Selecao selecao, Aluno aluno, Identificacao identificacao) throws AuxilioMoradiaException;

	Inscricao get(Aluno aluno, Selecao selecao);
	
	Inscricao salvar(Inscricao inscricao);

	Inscricao atualizar(Inscricao inscricao) throws AuxilioMoradiaException;
	
	boolean editar(Inscricao inscricao);

    PessoaFamilia adicionarMembroFamilia(PessoaFamilia pessoa);

    void removerMembroFamilia(PessoaFamilia pessoa);

    List<Inscricao> getAll(Aluno aluno);

	List<Inscricao> getAllOrdenado(Selecao selecao);
	
	Inscricao buscarInscricaoPorId(Integer idInscricao);
		
	boolean selecionarInscricao(Integer idInscricao,Integer selecionar);
		
	
}

