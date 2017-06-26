package br.ufc.npi.auxilio.enums;

public enum ParticipouSelecaoResultado {
	SIM_DEFERIDO("Sim, foi deferido"),
	SIM_LISTA("Sim, ficou na lista de espera"), 
	SIM_INDEFERIDO("Sim, mas foi indeferido"),
	NAO_PARTICIPOU("Não participou");
	
	private String nome;
	
	ParticipouSelecaoResultado(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
