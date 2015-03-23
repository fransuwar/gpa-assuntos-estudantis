package br.com.ufc.quixada.npi.gpa.enums;

import java.util.HashMap;
import java.util.Map;

public enum Status {
	NOVA("Nova"), SUBMETIDO("Submetido"), INSC_ABERTA("Aberta"), PROC_SELETIVO("Em processo seletivo"), FINALIZADA("Finalizada"), CANCELADA("Cancelada");
	
	
	private String nome;
	private static Map<Status, String> map;

	Status(String nome) {
		this.nome = nome;
	}

	public static Map<Status, String> toMap() {
		if (map == null) {
			map = new HashMap<Status, String>();
			for (Status s : Status.values()) {
				map.put(s, s.nome);
			}
		}
		return map;
	}
}
