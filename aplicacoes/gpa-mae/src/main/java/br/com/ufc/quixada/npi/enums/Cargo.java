package br.com.ufc.quixada.npi.enums;

public enum Cargo {
	PROFESSOR("Professor"), TECADMINISTRATIVO("Tec Administrativo");

	private String cargo;

	Cargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
}