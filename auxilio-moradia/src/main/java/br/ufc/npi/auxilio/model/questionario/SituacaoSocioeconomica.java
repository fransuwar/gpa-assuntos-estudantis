package br.ufc.npi.auxilio.model.questionario;

public class SituacaoSocioeconomica {

    // Com relação à saúde
    private boolean medicamento;

    private String doencaMedicamento;

    private boolean deficiencia;

    private String nomeDeficiencia;

    private boolean doencaGrave;

    private String membroDoencaGrave;

    private boolean membroDeficiencia;

    private String nomeMembroDeficiencia;

    private boolean assistenciaMedica;

    private Double valorAssistenciaMedica;

    private boolean despesaMedicamento;

    private String descricaoDespesaMedicamento;

    private boolean beneficio;

    private String descricaoBeneficio;

    public boolean isMedicamento() {
        return medicamento;
    }

    public void setMedicamento(boolean medicamento) {
        this.medicamento = medicamento;
    }

    public String getDoencaMedicamento() {
        return doencaMedicamento;
    }

    public void setDoencaMedicamento(String doencaMedicamento) {
        this.doencaMedicamento = doencaMedicamento;
    }

    public boolean isDeficiencia() {
        return deficiencia;
    }

    public void setDeficiencia(boolean deficiencia) {
        this.deficiencia = deficiencia;
    }

    public String getNomeDeficiencia() {
        return nomeDeficiencia;
    }

    public void setNomeDeficiencia(String nomeDeficiencia) {
        this.nomeDeficiencia = nomeDeficiencia;
    }

    public boolean isDoencaGrave() {
        return doencaGrave;
    }

    public void setDoencaGrave(boolean doencaGrave) {
        this.doencaGrave = doencaGrave;
    }

    public String getMembroDoencaGrave() {
        return membroDoencaGrave;
    }

    public void setMembroDoencaGrave(String membroDoencaGrave) {
        this.membroDoencaGrave = membroDoencaGrave;
    }

    public boolean isMembroDeficiencia() {
        return membroDeficiencia;
    }

    public void setMembroDeficiencia(boolean membroDeficiencia) {
        this.membroDeficiencia = membroDeficiencia;
    }

    public String getNomeMembroDeficiencia() {
        return nomeMembroDeficiencia;
    }

    public void setNomeMembroDeficiencia(String nomeMembroDeficiencia) {
        this.nomeMembroDeficiencia = nomeMembroDeficiencia;
    }

    public boolean getAssistenciaMedica() {
        return assistenciaMedica;
    }

    public void setAssistenciaMedica(boolean assistenciaMedica) {
        this.assistenciaMedica = assistenciaMedica;
    }

    public Double getValorAssistenciaMedica() {
        return valorAssistenciaMedica;
    }

    public void setValorAssistenciaMedica(Double valorAssistenciaMedica) {
        this.valorAssistenciaMedica = valorAssistenciaMedica;
    }

    public boolean isAssistenciaMedica() {
        return assistenciaMedica;
    }

    public boolean isDespesaMedicamento() {
        return despesaMedicamento;
    }

    public void setDespesaMedicamento(boolean despesaMedicamento) {
        this.despesaMedicamento = despesaMedicamento;
    }

    public String getDescricaoDespesaMedicamento() {
        return descricaoDespesaMedicamento;
    }

    public void setDescricaoDespesaMedicamento(String descricaoDespesaMedicamento) {
        this.descricaoDespesaMedicamento = descricaoDespesaMedicamento;
    }

    public boolean isBeneficio() {
        return beneficio;
    }

    public void setBeneficio(boolean beneficio) {
        this.beneficio = beneficio;
    }

    public String getDescricaoBeneficio() {
        return descricaoBeneficio;
    }

    public void setDescricaoBeneficio(String descricaoBeneficio) {
        this.descricaoBeneficio = descricaoBeneficio;
    }
}
