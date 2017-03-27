package br.ufc.npi.auxilio.enums;

public enum Escolaridade {
	SEM_ESCOLARIDADE("Sem Escolaridade"), FUNDAMENTAL_INCOMPLETO("Ensino Fundamental Incompleto"), 
	FUNDAMENTAL_COMPLETO("Ensino Fundamental Completo"), MEDIO_INCOMPLETO("Ensino Médio Incompleto"), 
	MEDIO_COMPLETO("Ensino Médio Completo"), SUPERIOR_INCOMPLETO("Ensino Superior Incompleto"),
	SUPERIOR_COMPLETO("Ensino Superior Completo");
	
	private String nome;
	
	Escolaridade (String nome){
		this.nome = nome;
	}
	
	public String getNome(){
		return nome;
	}
}
