package br.ufc.quixada.npi.gpa.enums;


public enum GrauParentesco {
	FILHO("Filho(a)"), NETO("Neto(a)"), SOBRINHO("Sobrinho(a)"), IRMAO("Irmão"), CONJ_COMP(
			"Cônjuge ou Companheiro(a)"), OUTROS("Outros");

	private String nome;

	private GrauParentesco(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}


}
