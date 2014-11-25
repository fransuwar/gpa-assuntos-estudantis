package br.com.ufc.quixada.npi.gpa.enums;

public enum TipoEnsinoFundamental{
	Publico("Público"), Particular("Particular"), Particular_Com_Bolsa("Particular com Bolsa");
	private String nome;
	
	TipoEnsinoFundamental(String nome){
		this.nome = nome;
	}
	public String getNome(){
		return nome;
	}
} 
