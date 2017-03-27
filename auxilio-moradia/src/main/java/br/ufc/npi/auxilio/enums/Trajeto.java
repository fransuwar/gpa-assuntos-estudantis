package br.ufc.npi.auxilio.enums;

public enum Trajeto {

    PE("A pé"), BICICLETA("Bicicleta"), TRANSPORTE_PUBLICO("Transporte público"), CARONA("Carona"),
    TRANSPORTE_FRETADO("Transporte fretado"), VEICULO_PROPRIO("Veículo próprio"), OUTROS("Outros");

    private String nome;

    Trajeto (String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }

}
