package br.ufc.quixada.npi.gpa.enums;

public enum Banco {
	BB("Banco do Brasil"), CX("Caixa");

	private String nome;
	
	Banco(String nome) {
		this.nome = nome;
	}


	public String getNome() {
		return nome;
	}
	
}
