package br.com.ufc.quixada.npi.gpa.enums;

import java.util.HashMap;
import java.util.Map;

public enum SituacaoResidencia {
	CEDIDO("Cedido"), PROPRIO("Próprio"), ALUGADO("Alugado"), DOADO("Doado");

	private String nome;
	private static Map<SituacaoResidencia, String> map;

	SituacaoResidencia(String nome) {
		this.nome = nome;
	}

	public static Map<SituacaoResidencia, String> toMap() {
		if (map == null) {
			map = new HashMap<SituacaoResidencia, String>();
			for (SituacaoResidencia sr : SituacaoResidencia.values()) {
				map.put(sr, sr.nome);
			}
		}
		return map;
	}
}
