package br.ufc.npi.auxilio.enums;

public enum HorarioEntrevista {
    AB("AB"), CD("CD"), ABCD("TODOS");
	
	private String nome;

	HorarioEntrevista (String nome){
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

}
