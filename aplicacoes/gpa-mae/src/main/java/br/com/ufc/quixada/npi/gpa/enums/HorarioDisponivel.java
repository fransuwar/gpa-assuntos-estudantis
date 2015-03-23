package br.com.ufc.quixada.npi.gpa.enums;

import java.util.HashMap;
import java.util.Map;

public enum HorarioDisponivel {
	M("Manhã"), T("Tarde"), N("Noite");

	private String nome;
	private static Map<HorarioDisponivel, String> map;

	HorarioDisponivel(String nome) {
		this.nome = nome;
	}

	public static Map<HorarioDisponivel, String> toMap() {
		if (map == null) {
			map = new HashMap<HorarioDisponivel, String>();
			for (HorarioDisponivel hd : HorarioDisponivel.values()) {
				map.put(hd, hd.nome);
			}
		}
		return map;
	}
}
