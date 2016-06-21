package br.ufc.quixada.npi.gpa.enums;


public enum GrauParentesco {
	PAI("Pai"), MAE("Mãe"), IRMAO("Irmão(ã)"), FILHO("Filho(a)"), CONJ_COMP("Cônjuge ou Companheiro(a)"), OUTRO("Outro");

	private String nome;

	private GrauParentesco(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}


}
