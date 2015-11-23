package br.ufc.quixada.npi.gpa.enums;

import java.util.Map;
import java.util.TreeMap;

public enum Resultado {
	DEFERIDO("deferido"),INDEFERIDO("indeferido");
	
	private String nome;
	private static Map<Resultado, String> map;
	Resultado(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}


	public static Map<Resultado, String> toMap() {
		if (map == null) {
			map = new TreeMap<Resultado, String>();
			for (Resultado result : Resultado.values()) {
				map.put(result, result.nome);
			}
		}
		return map;
	}

}
