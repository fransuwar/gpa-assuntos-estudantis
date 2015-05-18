package br.ufc.quixada.npi.gpa.enums;

import java.util.Map;
import java.util.TreeMap;

public enum Banco {
	BB("Banco do Brasil"), CX("Caixa");

	private String nome;
	private static Map<Banco, String> map;

	Banco(String nome) {
		this.nome = nome;
	}

	public static Map<Banco, String> toMap() {
		if (map == null) {
			map = new TreeMap<Banco, String>();
			for (Banco b : Banco.values()) {
				map.put(b, b.nome);
			}
		}
		return map;
	}

	public String getNome() {
		return nome;
	}
	
}
