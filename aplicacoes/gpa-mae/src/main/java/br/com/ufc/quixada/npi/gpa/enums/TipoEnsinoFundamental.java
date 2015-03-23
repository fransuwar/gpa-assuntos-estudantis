package br.com.ufc.quixada.npi.gpa.enums;

import java.util.HashMap;
import java.util.Map;

public enum TipoEnsinoFundamental{
	PUBLICO("Público"), PARTICULAR("Particular"), PART_COM_BOLSA("Particular com Bolsa");
	
	private String nome;
	private static Map<TipoEnsinoFundamental, String> map;

	TipoEnsinoFundamental(String nome) {
		this.nome = nome;
	}

	public static Map<TipoEnsinoFundamental, String> toMap() {
		if (map == null) {
			map = new HashMap<TipoEnsinoFundamental, String>();
			for (TipoEnsinoFundamental tef : TipoEnsinoFundamental.values()) {
				map.put(tef, tef.nome);
			}
		}
		return map;
	}
} 
