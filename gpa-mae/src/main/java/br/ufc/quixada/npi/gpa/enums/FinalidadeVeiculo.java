package br.ufc.quixada.npi.gpa.enums;

public enum FinalidadeVeiculo {
	PASSEIO("Passeio"), TAXI("Táxi"), FRETE("Frete"), OUTRA("Outra");

	private String nome;

	FinalidadeVeiculo(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

}
