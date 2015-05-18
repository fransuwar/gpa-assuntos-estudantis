package br.ufc.quixada.npi.gpa.enums;

import java.util.Map;
import java.util.TreeMap;

public enum DiasUteis {

	SEG("Segunda"), TER("Terça"), QUA("Quarta"), QUI("Quinta"), SEX("Sexta");
	
	private String nome;
	private static Map<DiasUteis, String> map;
	
	private DiasUteis(String nome){
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public static Map<DiasUteis, String> toMap() {
		if (map == null) {
			map = new TreeMap<DiasUteis, String>();
			for (DiasUteis du : DiasUteis.values()) {
				map.put(du, du.nome);
			}
		}
		return map;
	}
}
