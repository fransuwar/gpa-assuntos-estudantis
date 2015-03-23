package br.com.ufc.quixada.npi.gpa.enums;

import java.util.HashMap;
import java.util.Map;

public enum FinalidadeVeiculo {
	PASSEIO("Passeio"), TAXI("Táxi"), FRETE("Frete");

	private String nome;
	private static Map<FinalidadeVeiculo, String> map;

	FinalidadeVeiculo(String nome) {
		this.nome = nome;
	}

	public static Map<FinalidadeVeiculo, String> toMap() {
		if (map == null) {
			map = new HashMap<FinalidadeVeiculo, String>();
			for (FinalidadeVeiculo fv : FinalidadeVeiculo.values()) {
				map.put(fv, fv.nome);
			}
		}
		return map;
	}

}
