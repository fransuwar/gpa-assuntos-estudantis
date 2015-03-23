package br.com.ufc.quixada.npi.gpa.enums;

import java.util.HashMap;
import java.util.Map;

public enum TipoBolsa {
	INIC_ACAD("Iniciação Acadêmica"), AUX_MOR("Auxilio Moradia");

	private String nome;
	private static Map<TipoBolsa, String> map;

	TipoBolsa(String nome) {
		this.nome = nome;
	}

	public static Map<TipoBolsa, String> toMap() {
		if (map == null) {
			map = new HashMap<TipoBolsa, String>();
			for (TipoBolsa tb : TipoBolsa.values()) {
				map.put(tb, tb.nome);
			}
		}
		return map;
	}
}
