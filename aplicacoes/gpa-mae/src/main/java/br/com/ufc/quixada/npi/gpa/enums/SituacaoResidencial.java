package br.com.ufc.quixada.npi.gpa.enums;

public enum SituacaoResidencial{
	cedido("Cedido"), proprio("Próprio"), alugado("Alugado"), doado("Doado");
	private String nome;
	SituacaoResidencial(String nome){
		this.nome = nome;
	}
	
	public String getNome(){
		return nome;
	}
}
