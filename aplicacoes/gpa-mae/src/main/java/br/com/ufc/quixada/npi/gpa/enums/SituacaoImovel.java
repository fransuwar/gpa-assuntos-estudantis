package br.com.ufc.quixada.npi.gpa.enums;

public enum SituacaoImovel{
	Cedido("Cedido"), Alugado("Alugado"), Proprio("Próprio"), Financiado("Financiado");
	
	private String imovel;
	
	SituacaoImovel(String imovel){
		this.imovel = imovel;
	}
	
	public String getImovel(){
		return imovel;
	}
}
