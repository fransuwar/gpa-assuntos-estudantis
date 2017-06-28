package br.ufc.npi.auxilio.enums;

public enum Turno {
	MANHA("MANHÃ"), TARDE("TARDE"), NOITE("NOITE");
	
	private String nome;

	Turno (String nome){
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

}
