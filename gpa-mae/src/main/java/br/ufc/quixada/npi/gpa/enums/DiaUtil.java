package br.ufc.quixada.npi.gpa.enums;

import java.util.Map;
import java.util.TreeMap;

public enum DiaUtil {

	SEG("Segunda"), TER("Terça"), QUA("Quarta"), QUI("Quinta"), SEX("Sexta");
	private String nome;
	private static Map<DiaUtil, String> map;

	private DiaUtil(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public static Map<DiaUtil, String> toMap() {
		if (map == null) {
			map = new TreeMap<DiaUtil, String>();
			for (DiaUtil du : DiaUtil.values()) {
				map.put(du, du.nome);
			}
		}
		return map;
	}
	
}
