package br.ufc.quixada.npi.gpa.enums;

import java.util.Map;
import java.util.TreeMap;

public enum Turno {
	M("Manhã"), T("Tarde"), N("Noite");

	private String nome;
	private static Map<Turno, String> map;

	Turno(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}


	public static Map<Turno, String> toMap() {
		if (map == null) {
			map = new TreeMap<Turno, String>();
			for (Turno hd : Turno.values()) {
				map.put(hd, hd.nome);
			}
		}
		return map;
	}
}
