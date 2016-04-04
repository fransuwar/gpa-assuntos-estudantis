package br.ufc.quixada.npi.gpa.enums;

import java.util.Map;
import java.util.TreeMap;

public enum SituacaoResidencia {
	CEDIDO("Cedido"), PROPRIO("Próprio"), ALUGADO("Alugado"), DOADO("Doado");

	private String nome;
	private static Map<SituacaoResidencia, String> map;

	SituacaoResidencia(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}


	public static Map<SituacaoResidencia, String> toMap() {
		if (map == null) {
			map = new TreeMap<SituacaoResidencia, String>();
			for (SituacaoResidencia sr : SituacaoResidencia.values()) {
				map.put(sr, sr.nome);
			}
		}
		return map;
	}
}
