package br.ufc.quixada.npi.gpa.enums;

public enum Turno {
	
	M("Manhã"), 
	T("Tarde"), 
	N("Noite");

	private String nome;

	private Turno(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
