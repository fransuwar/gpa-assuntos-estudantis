package br.ufc.npi.auxilio.enums;

import java.util.Map;
import java.util.TreeMap;

public enum SituacaoImovel{
	CEDIDO("Cedido"), ALUGADO("Alugado"), PROPRIO("Próprio"), FINANCIADO("Financiado");
	
	private String nome;
	private static Map<SituacaoImovel, String> map;

	SituacaoImovel(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
