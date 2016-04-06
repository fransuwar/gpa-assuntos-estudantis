package br.ufc.quixada.npi.gpa.enums;

public enum MoraCom {
	PAI("Pai"), MAE("Mãe"), IRMAOS("Irmãos"), PARENTES("Parentes"), CONJ_COMP("Cônjuge ou Companheiro(a)"), FILHOS(
			"Filhos(as)"), OUTRO("Outros");

	private String nome;

	MoraCom(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
