package br.com.ufc.quixada.npi.gpa.enums;

public enum TipoEnsinoMedio{
	Publico("Público"), Particular("Particular"), Particular_Com_Bolsa("Particular com Bolsa");
	private String media;	
	
	TipoEnsinoMedio(String media){
		this.media = media;
	}
	public String getMedia(){
		return media;
	}
}

	
