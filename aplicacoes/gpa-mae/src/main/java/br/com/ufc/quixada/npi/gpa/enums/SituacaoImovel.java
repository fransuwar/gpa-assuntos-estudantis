package br.com.ufc.quixada.npi.gpa.enums;

import java.util.HashMap;
import java.util.Map;

public enum SituacaoImovel{
	CEDIDO("Cedido"), ALUGADO("Alugado"), PROPRIO("Próprio"), FINANCIADO("Financiado");
	
	private String nome;
	private static Map<SituacaoImovel, String> map;

	SituacaoImovel(String nome) {
		this.nome = nome;
	}

	public static Map<SituacaoImovel, String> toMap() {
		if (map == null) {
			map = new HashMap<SituacaoImovel, String>();
			for (SituacaoImovel si : SituacaoImovel.values()) {
				map.put(si, si.nome);
			}
		}
		return map;
	}
}
