package br.com.ufc.quixada.npi.gpa.enums;

public enum GrauParentescoImovelRural{
	SemPropriedadeRural("Sem Propriedade Rural"), Pai("Pai"), Avô("Avô"), Tio("Tio"), Irmao("Irmão"),
	Conjuge("Cônjuge ou Companheiro(a)"), Outros("Outros");

	private String imovelRural;
	
	GrauParentescoImovelRural(String imovelRural){
		this.imovelRural = imovelRural;
	}
	
	public String getImovelRural(){
		return imovelRural;
	}
}
