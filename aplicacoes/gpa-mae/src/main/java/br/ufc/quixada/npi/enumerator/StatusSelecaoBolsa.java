package br.ufc.quixada.npi.enumerator;

public enum StatusSelecaoBolsa {
	NOVO("nova"), SUBMETIDA("submetida");
	
	private String descricao;

	private StatusSelecaoBolsa(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	
	
	
}
