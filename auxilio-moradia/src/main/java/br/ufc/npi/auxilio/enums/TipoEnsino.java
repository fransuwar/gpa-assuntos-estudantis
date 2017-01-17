package br.ufc.npi.auxilio.enums;

import java.util.Map;
import java.util.TreeMap;

public enum TipoEnsino{
	PUBLICO("Escola pública"), PARTICULAR("Escola particular"),
	PUBLICO_PARTICULAR("Parte em escola pública, parte em particular");
	
	private String nome;

	TipoEnsino(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
} 
