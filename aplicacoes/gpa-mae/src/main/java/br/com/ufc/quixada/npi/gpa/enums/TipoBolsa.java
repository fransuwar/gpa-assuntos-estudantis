package br.com.ufc.quixada.npi.gpa.enums;

public enum TipoBolsa {
	INICIACAO_ACADEMICA("Iniciação Acadêmica"), AUXILIO_MORADIA(
			"Auxilio Moradia");
	private String tipo;

	TipoBolsa(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
