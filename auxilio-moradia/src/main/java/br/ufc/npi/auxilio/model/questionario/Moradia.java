package br.ufc.npi.auxilio.model.questionario;

public class Moradia {

    private String mae;

    private String pai;

    public Moradia() {

    }

    public Moradia(String mae, String pai) {
        this.mae = mae;
        this.pai = pai;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }
}
