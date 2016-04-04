package br.ufc.quixada.npi.gpa.enums;

import java.util.Map;
import java.util.TreeMap;

public enum TipoEnsinoMedio{
	PUBLICO("Público"), PARTICULAR("Particular"), PART_COM_BOLSA("Particular com Bolsa");
	
	private String nome;
	private static Map<TipoEnsinoMedio, String> map;

	TipoEnsinoMedio(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public static Map<TipoEnsinoMedio, String> toMap() {
		if (map == null) {
			map = new TreeMap<TipoEnsinoMedio, String>();
			for (TipoEnsinoMedio tem : TipoEnsinoMedio.values()) {
				map.put(tem, tem.nome);
			}
		}
		return map;
	}
}

	
