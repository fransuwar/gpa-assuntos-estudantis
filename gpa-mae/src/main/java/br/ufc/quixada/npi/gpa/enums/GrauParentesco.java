package br.ufc.quixada.npi.gpa.enums;


public enum GrauParentesco {
	EU("Eu"), PAI("Pai"), MAE("Mãe"), IRMAO("Irmão(ã)"), FILHO("Filho(a)"), CONJ_COMP("Cônjuge ou Companheiro(a)"), OUTRO("Outro");

	private String nome;

	private GrauParentesco(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public static GrauParentesco[] getTodos(){
		return GrauParentesco.values();
	}

	public static GrauParentesco[] getTodosExcetoEu(){
		
		GrauParentesco[] todosParentescos = GrauParentesco.values();
		GrauParentesco[] parentescosRetorno = new GrauParentesco[GrauParentesco.values().length-1];
		
		int cont = 0;
		
		for(GrauParentesco grauParentesco:todosParentescos){
			if(grauParentesco != GrauParentesco.EU){
				parentescosRetorno[cont++] = grauParentesco;
			}
		}
		
		return parentescosRetorno;
	}
}
