package br.ufc.quixada.npi.gpa.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.ufc.quixada.npi.gpa.enums.Escolaridade;
import br.ufc.quixada.npi.gpa.enums.GrauParentesco;

@Entity
public class PessoaFamilia {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String nome;

	@Enumerated(EnumType.STRING)
	private Escolaridade escolaridade;

	private String profissao;

	private Double rendaMensal;

	@Enumerated(EnumType.STRING)
	private GrauParentesco parentesco;
	
	private String outroParentesco;

	public PessoaFamilia clone(){
		PessoaFamilia pessoa = new PessoaFamilia();
		pessoa.setNome(nome);
		pessoa.setEscolaridade(escolaridade);
		pessoa.setProfissao(profissao);
		pessoa.setRendaMensal(rendaMensal);
		pessoa.setParentesco(parentesco);
		
		return pessoa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Escolaridade getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String atividadeProfissao) {
		this.profissao = atividadeProfissao;
	}

	public Double getRendaMensal() {
		return rendaMensal;
	}

	public void setRendaMensal(Double rendaMensal) {
		this.rendaMensal = rendaMensal;
	}

	public GrauParentesco getParentesco() {
		return parentesco;
	}

	public void setParentesco(GrauParentesco grauParentesco) {
		this.parentesco = grauParentesco;
	}

	public String getOutroParentesco() {
		return outroParentesco;
	}

	public void setOutroParentesco(String outroParentesco) {
		this.outroParentesco = outroParentesco;
	}
	
	
}
