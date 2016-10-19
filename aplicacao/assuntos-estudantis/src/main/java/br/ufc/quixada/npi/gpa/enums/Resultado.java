package br.ufc.quixada.npi.gpa.enums;

public enum Resultado {
	DEFERIDO("deferido"),INDEFERIDO("indeferido"), NAO_AVALIADO("não avaliado");
	
	private String nome;
	
	Resultado(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
