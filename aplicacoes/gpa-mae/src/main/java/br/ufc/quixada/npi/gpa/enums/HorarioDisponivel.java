package br.ufc.quixada.npi.gpa.enums;

import java.util.Map;
import java.util.TreeMap;

public enum HorarioDisponivel {
	M("Manhã"), T("Tarde"), N("Noite");

	private String nome;
	private static Map<HorarioDisponivel, String> map;

	HorarioDisponivel(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}


	public static Map<HorarioDisponivel, String> toMap() {
		if (map == null) {
			map = new TreeMap<HorarioDisponivel, String>();
			for (HorarioDisponivel hd : HorarioDisponivel.values()) {
				map.put(hd, hd.nome);
			}
		}
		return map;
	}
}
