package br.com.ufc.quixada.npi.gpa.enums;

public enum SituacaoResidencia {
	cedido("Cedido"), proprio("Próprio"), alugado("Alugado"), doado("Doado");

	private String nome;

	SituacaoResidencia(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
