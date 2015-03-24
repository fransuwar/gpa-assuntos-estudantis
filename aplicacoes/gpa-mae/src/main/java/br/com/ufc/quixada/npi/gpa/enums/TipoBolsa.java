package br.com.ufc.quixada.npi.gpa.enums;

import java.util.Map;
import java.util.TreeMap;

public enum TipoBolsa {
	INIC_ACAD("Iniciação Acadêmica"), AUX_MOR("Auxilio Moradia");

	private String nome;
	private static Map<TipoBolsa, String> map;

	TipoBolsa(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}


	public static Map<TipoBolsa, String> toMap() {
		if (map == null) {
			map = new TreeMap<TipoBolsa, String>();
			for (TipoBolsa tb : TipoBolsa.values()) {
				map.put(tb, tb.nome);
			}
		}
		return map;
	}
}
