package br.ufc.npi.auxilio.enums;

public enum Modalidades {
	AMPLA_CONCORRENCIA("Candidatos que não utilizaram nenhum sistema de cotas"),
	MODALIDADE_L1("Candidatos que cursaram todo o ensino médio em escolas públicas, com renda familiar bruta per capita igual ou inferior a 1,5 salário mínimo"),
	MODALIDADE_L2("Candidatos que cursaram todo o ensino médio em escolas públicas, com renda familiar bruta per capita igual ou inferior a 1,5 salário mínimo, autodeclarados pretos, pardos ou indígenas"), 
	MODALIDADE_L3("Candidatos que cursaram todo o ensino médio em escolas públicas, independente da renda"),
	MODADLIDADE_L4("Candidatos que cursaram todo o ensino médio em escolas públicas, independente da renda, autodeclarados pretos, pardos ou indígenas");
	
	private String nome;
	
	Modalidades(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
