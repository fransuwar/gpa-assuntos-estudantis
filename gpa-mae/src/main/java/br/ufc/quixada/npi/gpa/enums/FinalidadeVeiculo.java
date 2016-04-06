package br.ufc.quixada.npi.gpa.enums;

import java.util.Map;
import java.util.TreeMap;

public enum FinalidadeVeiculo {
	PASSEIO("Passeio"), TAXI("Táxi"), FRETE("Frete");

	private String nome;
	private static Map<FinalidadeVeiculo, String> map;

	FinalidadeVeiculo(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}


	public static Map<FinalidadeVeiculo, String> toMap() {
		if (map == null) {
			map = new TreeMap<FinalidadeVeiculo, String>();
			for (FinalidadeVeiculo fv : FinalidadeVeiculo.values()) {
				map.put(fv, fv.nome);
			}
		}
		return map;
	}

}
