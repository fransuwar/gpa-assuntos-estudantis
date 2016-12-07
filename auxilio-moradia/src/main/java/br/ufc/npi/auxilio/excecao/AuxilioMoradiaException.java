package br.ufc.npi.auxilio.excecao;

public class AuxilioMoradiaException extends Exception {

    private String message;

    public AuxilioMoradiaException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
