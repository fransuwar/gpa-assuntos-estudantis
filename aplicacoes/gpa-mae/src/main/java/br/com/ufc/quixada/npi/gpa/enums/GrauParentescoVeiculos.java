package br.com.ufc.quixada.npi.gpa.enums;

public enum GrauParentescoVeiculos{
	SemVeiculo("Sem Veículo"), Pai("Pai"), Avô("Avô"), Tio("Tio"), Irmao("Irmão"),
	Conjuge("Cônjuge ou Companheiro(a)"), Outros("Outros");


	private String parentesco;
	
	GrauParentescoVeiculos(String parentesco){
		this.parentesco = parentesco;
	}
	
	public String getParentesco(){
		return parentesco;
	}
}
