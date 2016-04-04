package br.ufc.quixada.npi.gpa.enums;


public enum Curso {
	SI("Sistemas de Informação"), ES("Engenharia de Software"),
	RC("Redes de Computadores"), CC("Ciência da Computação"),
	EC("Engenharia da Computação"), DD("Design Digital");
	
	private String nome;
	
	Curso(String nome){
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

}
