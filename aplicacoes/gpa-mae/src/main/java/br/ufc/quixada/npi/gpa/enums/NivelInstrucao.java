package br.ufc.quixada.npi.gpa.enums;

public enum NivelInstrucao {

	ENS_FUND_COMP("Ensino Fundamental Completo"), ENS_MED_COMP("Ensino Médio Completo"), ENS_SUP_COMP(
			"Ensino Superior Completo"), ENS_FUND_INCOMP("Ensino Fundamental Incompleto"), ENS_MED_INCOMP(
					"Ensino Médio Incompleto"), ENS_SUP_INCOMP("Ensino Superior Incompleto");

	private String nome;

	NivelInstrucao(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
