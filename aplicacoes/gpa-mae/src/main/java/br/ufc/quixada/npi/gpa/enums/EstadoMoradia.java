package br.ufc.quixada.npi.gpa.enums;


public enum EstadoMoradia {
	CONCLUIDO("Concluído"), EM_CONST("Em construção"), DETERIORADO("Deteriorado");
	
	private String nome;
	
	private EstadoMoradia(String nome) {
		this.nome=nome;
	}
	
	public String getNome() {
		return nome;
	}
}
