package br.com.ufc.quixada.npi.gpa.enums;

import java.util.Map;
import java.util.TreeMap;

public enum Bancos {
	BB("Banco do Brasil"), CX("Caixa");

	private String nome;
	private static Map<Bancos, String> map;

	Bancos(String nome) {
		this.nome = nome;
	}

	public static Map<Bancos, String> toMap() {
		if (map == null) {
			map = new TreeMap<Bancos, String>();
			for (Bancos b : Bancos.values()) {
				map.put(b, b.nome);
			}
		}
		return map;
	}
}
