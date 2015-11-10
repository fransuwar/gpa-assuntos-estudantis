package br.ufc.quixada.npi.gpa.enums;

import java.util.Map;
import java.util.TreeMap;

public enum NivelInstrucao {

	ENS_FUND_COMP("Ensino Fundamental Completo"), ENS_MED_COMP("Ensino Médio Completo"), ENS_SUP_COMP(
			"Ensino Superior Completo"), ENS_FUND_INCOMP("Ensino Fundamental Incompleto"), ENS_MED_INCOMP(
					"Ensino Médio Incompleto"), ENS_SUP_INCOMP("Ensino Superior Incompleto");
	private static Map<NivelInstrucao, String> map;
	private String nome;
	public static Map<NivelInstrucao, String> toMap() {
		if (map == null) {
			map = new TreeMap<NivelInstrucao, String>();
			for (NivelInstrucao ni : NivelInstrucao.values()) {
				map.put(ni, ni.nome);
			}
		}
		return map;
	}

	NivelInstrucao(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
