package br.ufc.quixada.npi.gpa.enums;

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


	public static Map<SituacaoImovel, String> toMap() {
		if (map == null) {
			map = new TreeMap<SituacaoImovel, String>();
			for (SituacaoImovel si : SituacaoImovel.values()) {
				map.put(si, si.nome);
			}
		}
		return map;
	}
}
