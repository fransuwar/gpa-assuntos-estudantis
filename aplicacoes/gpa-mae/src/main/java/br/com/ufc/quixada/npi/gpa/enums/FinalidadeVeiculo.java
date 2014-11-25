package br.com.ufc.quixada.npi.gpa.enums;

public enum FinalidadeVeiculo{
	Passeio("Passeio"), Taxi("Táxi"), Frete("Frete");
	
	private String veiculo;
	
	FinalidadeVeiculo(String veiculo){
		this.veiculo = veiculo;
	}
	public String getVeiculo(){
		return veiculo;
	}
}
