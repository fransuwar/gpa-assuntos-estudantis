package br.ufc.quixada.npi.gpa.enums;

import java.util.Map;
import java.util.TreeMap;

public enum Curso {
	SI("Sistemas de Informação"), ES("Engenharia de Software"),
	RC("Redes de Computadores"), CC("Ciência da Computação"),
	EC("Engenharia da Computação"), DD("Design Digital");
	
	private String nome;
	private static Map<Curso, String> map;
	
	Curso(String nome){
		this.nome = nome;
	}
	
	public static Map<Curso, String> toMap(){
		if(map == null) {
			map = new TreeMap<Curso, String>();
			for (Curso c : Curso.values()) {
				map.put(c, c.nome);
			}
		}
		return map;
	}

	public String getNome() {
		return nome;
	}

}
