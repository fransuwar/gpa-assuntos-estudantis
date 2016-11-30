package br.ufc.npi.auxilio.enums;

import java.util.Map;
import java.util.TreeMap;

public enum Estado {
	AC("Acre"), AL("Alagoas"), AP("Amapa"), AM("Amazonas"), BA("Bahia"), CE(
			"Ceará"), DF("Distrito Federal"), ES("Espirito Santo"), GO("Goiás"), MA(
			"Maranhão"), MT("Mato Grosso"), MS("Mato Grosso do Sul"), MG(
			"Minas Gerais"), PA("Pará"), PB("Paraíba"), PR("Paraná"), PE(
			"Pernambuco"), PI("Piauí"), RJ("Rio de Janeiro"), RN(
			"Rio Grande do Norte"), RS("Rio Grande do Sul"), RO("Rondonia"), RR(
			"Roraima"), SC("Santa Catarina"), SP("São Paulo"), SE("Sergipe"), TO(
			"Tocantins");

	private String nome;
	private static Map<Estado, String> map;

	Estado(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public static Map<Estado, String> toMap() {
		if (map == null) {
			map = new TreeMap<Estado, String>();
			for (Estado e : Estado.values()) {
				map.put(e, e.nome);
			}
		}
		return map;
	}

}