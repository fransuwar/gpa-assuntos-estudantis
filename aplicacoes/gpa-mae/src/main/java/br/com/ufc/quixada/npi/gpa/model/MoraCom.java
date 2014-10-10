package br.com.ufc.quixada.npi.gpa.model;

public enum MoraCom {
	
	Pais("Pais"), Pai("Pai"), Mae("Mãe"), Irmaos("Irmãos"), Parentes("Parentes"),
	Conjuge_Companheiro("Cônjuge ou Companheiro(a)"), Filhos("Filhos(as)"), Outra_moradia("Outros");
	
	private String mora;
	
	MoraCom(String mora) {
		this.mora = mora;
	}
	
	public String getMora(){
		return mora;
	}
	
}
