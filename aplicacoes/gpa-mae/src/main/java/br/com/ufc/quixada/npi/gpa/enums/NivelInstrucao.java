package br.com.ufc.quixada.npi.gpa.enums;

public enum NivelInstrucao{

	EnsinoFundamentalCompleto("Ensino Fundamental Completo"), EnsinoMedioCompleto("Ensino Médio Completo"), EnsinoSuperiorCompleto("Ensino Superior Completo"), 
	EnsinoFundamentalIncompleto("Ensino Fundamental Incompleto"), EnsinoMedioIncompleto("Ensino Médio Incompleto"), EnsinoSuperiorIncompleto("Ensino Superior Incompleto");
	private String nome;
	NivelInstrucao(String tipo){
		this.nome = tipo;
	}
	
	public String getNome() {
		return nome;
	}
}
