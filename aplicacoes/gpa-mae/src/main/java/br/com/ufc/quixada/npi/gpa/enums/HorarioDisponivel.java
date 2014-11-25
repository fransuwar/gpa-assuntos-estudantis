package br.com.ufc.quixada.npi.gpa.enums;

public enum HorarioDisponivel{
	Manhã("Manhã"), Tarde("Tarde"), Noite("Noite");
	private String nome;
	private HorarioDisponivel(String nome){
		this.nome = nome;
	}
	public String getNome(){
		return nome;
	}
}
