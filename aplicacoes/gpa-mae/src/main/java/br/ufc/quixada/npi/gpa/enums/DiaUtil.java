package br.ufc.quixada.npi.gpa.enums;

public enum DiaUtil {

	SEG("Segunda"), 
	TER("Terça"),
	QUA("Quarta"), 
	QUI("Quinta"), 
	SEX("Sexta");
	
	private String nome;
	
	private DiaUtil(String nome){
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
}
