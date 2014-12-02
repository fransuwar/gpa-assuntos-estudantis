package br.com.ufc.quixada.npi.gpa.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
public class QuestionarioIniciacaoAcademica {

	public QuestionarioIniciacaoAcademica(){}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private SelecaoBolsa selecaoBolsa;
	
	public SelecaoBolsa getSelecaoBolsa() {
		return selecaoBolsa;
	}
	public void setSelecaoBolsa(SelecaoBolsa selecaoBolsa) {
		this.selecaoBolsa = selecaoBolsa;
	}

	@Column(nullable = false)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name= "iniciacaoacademica_id")
	private List<PessoaFamilia> pessoas;
	
	@NotNull
	@Size(min=5, message="Preenchimento Obrigatório")
	private String enderecoAtual;
	private int numero;
	private String complemento;
	@NotNull
	@Size(min=1,message="Preenchimento Obrigatório")
	private String bairro;
	private String uf;
	@Pattern(regexp="^[0-9]{8}$", message="CEP Inválido")
	private String cep;
	@NotNull
	@Size(min=3,message="Preenchimento Obrigatório")
	private String cidade;
	private String pontoReferencia;
	@Pattern(regexp="\\(?\\b([0-9]{2})\\)?[-. ]?([0-9]{4})[-. ]?([0-9]{4})\\b", message="Telefone Incorreto formato (00) 0000-0000")
	private String telefoneFixo;
	@Pattern(regexp="\\(?\\b([0-9]{2})\\)?[-. ]?([0-9]{4})[-. ]?([0-9]{4})\\b", message="Telefone Incorreto formato (00) 0000-0000")
	private String telefoneCelular;
	private String email;
	
	@NotNull
	@Size(min=1,message="Preenchimento Obrigatório")
	private String enderecoFamilia;
	private int numeroFamilia;
	@NotNull
	@Size(min=1,message="Preenchimento Obrigatório")
	private String bairroFamilia;
	private String ufFamilia;
	private String complementoFamilia;
	@Pattern(regexp="^[0-9]{8}$", message="CEP Inválido")
	private String cepFamilia;
	@NotNull
	@Size(min=3,message="Preenchimento Obrigatório")
	private String cidadeFamilia;
	private String pontoReferenciaFamilia;
	
	private int anosEstudoPrivado;
	
	
	public enum NivelInstrucao{

		EnsinoFundamentalCompleto("Ensino Fundamental Completo"), EnsinoMedioCompleto("Ensino Médio Completo"), EnsinoSuperiorCompleto("Ensino Superior Completo"), 
		EnsinoFundamentalIncompleto("Ensino Fundamental Incompleto"), EnsinoMedioIncompleto("Ensino Médio Incompleto"), EnsinoSuperiorIncompleto("Ensino Superior Incompleto");
		private String nome;
		NivelInstrucao(String tipo){
			this.nome = tipo;
		}
		
		public String getNome() {
			return nome;
		}
	}
	
	@Enumerated(EnumType.STRING)
	private NivelInstrucao nivelInstrucaoMae;
	
	
	private NivelInstrucao nivelInstrucaoPai;
	@NotNull
	@Size(min=1,message="Preenchimento Obrigatório")
	private String resideAtualmente;
	
	@NotNull
	@Size(min=1,message="Preenchimento Obrigatório")
	private String definicaoLocalAtual;
	
	public enum SituacaoResidencia{
		cedido("Cedido"), proprio("Próprio"), alugado("Alugado"), doado("Doado");
		private String nome;
		SituacaoResidencia(String nome){
			this.nome = nome;
		}
		
		public String getNome(){
			return nome;
		}
	}
	
	public enum Estado {
		Acre("Acre"), Alagoas("Alagoas"), Amapa("Amapa"), Amazonas("Amazonas"), Bahia("Bahia"), Ceara("Ceará"), Distrito_Federal("Distrito Federal"),
		Espirito_Santo("Espirito Santo"), Goias("Goiás"), Maranhao("Maranhão"), Mato_Grosso("Mato Grosso"), Mato_Grosso_do_Sul("Mato Grosso do Sul"),
		Minas_Gerais("Minas Gerais"), Para("Pará"), Paraiba("Paraíba"), Parana("Paraná"), Pernambuco("Pernambuco"), Piaui("Piauí"),
		Rio_de_Janeiro("Rio de Janeiro"), Rio_Grande_do_Norte("Rio Grande do Norte"), Rio_Grande_do_Sul("Rio Grande do Sul"), Rondonia("Rondonia"), Roraima("Roraima"),
		Santa_Catarina("Santa Catarina"), Sao_Paulo("São Paulo"), Sergipe("Sergipe"), Tocantins("Tocantins");
		private String estado;
		Estado(String estado){
		this.estado = estado;
		}
		public String getEstado(){
			return estado;
		}
	}
	
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	@Enumerated(EnumType.STRING)
	private Estado estadoFamilia;
	
	@Enumerated(EnumType.STRING)
	private SituacaoResidencia situacaoResidencia;
	
	
	private int qtdAparelhoSom;
	private int qtdTelevisao;
	private int qtdRadio;
	private int qtdAutomovel;
	private int qtdMotocicleta;
	private int qtdMaquinaLavar;
	private int qtdDvdVideocassete;
	private int qtdGeladeira;
	private int qtdFreezer;
	private int qtdTelefoneFixo;
	private int qtdCelularResidentes;
	private int qtdComputador;
	private int qtdFogaoGas;
	private int qtdMaquinaCostura;
	private int qtdComodosSemBanheiro;
	private int qtdBanheiros;
	private int qtdEmpregadosDomesticos;
	private int totalMembrosFamilia;
	
	private String nome;
	public enum GrauParentesco{
		Filho_a("Filho(a)"), Neto("Neto(a)"), Sobrinho("Sobrinho(a)"), Irmao("Irmão"),
		Conjuge_Companheiro("Cônjuge ou Companheiro(a)"), Outros("Outros");
		
		private GrauParentesco(String nome){}
	}
	
	private GrauParentesco parentesco;
	
	private int idade;
	private String atividade;
	private float renda;
	private float rendaTotalFamilia;
	public enum HorarioDisponivel{
		Manhã("Manhã"), Tarde("Tarde"), Noite("Noite");
		private String nome;
		private HorarioDisponivel(String nome){
			this.nome = nome;
		}
		public String getNome(){
			return nome;
		}
	}
	
	@Enumerated(EnumType.STRING)
	private HorarioDisponivel horariodisponivelBolsa;
	@NotNull
	@Size(min=1,message="Preenchimento Obrigatório")
	private String justificativaPedido;
	
	@Pattern(regexp="\\(?\\b([0-9]{2})\\)?[-. ]?([0-9]{4})[-. ]?([0-9]{4})\\b", message="Telefone Incorreto formato (00) 0000-0000")
	private String telefoneFixoFamilia;
	@Pattern(regexp="\\(?\\b([0-9]{2})\\)?[-. ]?([0-9]{4})[-. ]?([0-9]{4})\\b", message="Telefone Incorreto formato (00) 0000-0000")
	private String telefoneCelularFamilia;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<PessoaFamilia> getPessoas() {
		return pessoas;
	}
	public void setPessoas(List<PessoaFamilia> pessoas) {
		this.pessoas = pessoas;
	}
	public String getEnderecoAtual() {
		return enderecoAtual;
	}
	public void setEnderecoAtual(String enderecoAtual) {
		this.enderecoAtual = enderecoAtual;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getPontoReferencia() {
		return pontoReferencia;
	}
	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}
	public String getTelefoneFixo() {
		return telefoneFixo;
	}
	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}
	public String getTelefoneCelular() {
		return telefoneCelular;
	}
	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEnderecoFamilia() {
		return enderecoFamilia;
	}
	public void setEnderecoFamilia(String enderecoFamilia) {
		this.enderecoFamilia = enderecoFamilia;
	}
	public int getNumeroFamilia() {
		return numeroFamilia;
	}
	public void setNumeroFamilia(int numeroFamilia) {
		this.numeroFamilia = numeroFamilia;
	}
	public String getBairroFamilia() {
		return bairroFamilia;
	}
	public void setBairroFamilia(String bairroFamilia) {
		this.bairroFamilia = bairroFamilia;
	}
	public String getUfFamilia() {
		return ufFamilia;
	}
	public void setUfFamilia(String ufFamilia) {
		this.ufFamilia = ufFamilia;
	}
	public String getComplementoFamilia() {
		return complementoFamilia;
	}
	public void setComplementoFamilia(String complementoFamilia) {
		this.complementoFamilia = complementoFamilia;
	}
	public String getCepFamilia() {
		return cepFamilia;
	}
	public void setCepFamilia(String cepFamilia) {
		this.cepFamilia = cepFamilia;
	}
	public String getCidadeFamilia() {
		return cidadeFamilia;
	}
	public void setCidadeFamilia(String cidadeFamilia) {
		this.cidadeFamilia = cidadeFamilia;
	}
	public String getPontoReferenciaFamilia() {
		return pontoReferenciaFamilia;
	}
	public void setPontoReferenciaFamilia(String pontoReferenciaFamilia) {
		this.pontoReferenciaFamilia = pontoReferenciaFamilia;
	}
	public int getAnosEstudoPrivado() {
		return anosEstudoPrivado;
	}
	public void setAnosEstudoPrivado(int anosEstudoPrivado) {
		this.anosEstudoPrivado = anosEstudoPrivado;
	}
	public NivelInstrucao getNivelInstrucaoMae() {
		return nivelInstrucaoMae;
	}
	public void setNivelInstrucaoMae(NivelInstrucao nivelInstrucaoMae) {
		this.nivelInstrucaoMae = nivelInstrucaoMae;
	}
	public NivelInstrucao getNivelInstrucaoPai() {
		return nivelInstrucaoPai;
	}
	public void setNivelInstrucaoPai(NivelInstrucao nivelInstrucaoPai) {
		this.nivelInstrucaoPai = nivelInstrucaoPai;
	}
	public String getResideAtualmente() {
		return resideAtualmente;
	}
	public void setResideAtualmente(String resideAtualmente) {
		this.resideAtualmente = resideAtualmente;
	}
	public String getDefinicaoLocalAtual() {
		return definicaoLocalAtual;
	}
	public void setDefinicaoLocalAtual(String definicaoLocalAtual) {
		this.definicaoLocalAtual = definicaoLocalAtual;
	}
	public SituacaoResidencia getSituacaoResidencia() {
		return situacaoResidencia;
	}
	public void setSituacaoResidencia(SituacaoResidencia situacaoResidencia) {
		this.situacaoResidencia = situacaoResidencia;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Estado getEstadoFamilia() {
		return estadoFamilia;
	}
	public void setEstadoFamilia(Estado estadoFamilia) {
		this.estadoFamilia = estadoFamilia;
	}
	public int getQtdAparelhoSom() {
		return qtdAparelhoSom;
	}
	public void setQtdAparelhoSom(int qtdAparelhoSom) {
		this.qtdAparelhoSom = qtdAparelhoSom;
	}
	public int getQtdTelevisao() {
		return qtdTelevisao;
	}
	public void setQtdTelevisao(int qtdTelevisao) {
		this.qtdTelevisao = qtdTelevisao;
	}
	public int getQtdRadio() {
		return qtdRadio;
	}
	public void setQtdRadio(int qtdRadio) {
		this.qtdRadio = qtdRadio;
	}
	public int getQtdAutomovel() {
		return qtdAutomovel;
	}
	public void setQtdAutomovel(int qtdAutomovel) {
		this.qtdAutomovel = qtdAutomovel;
	}
	public int getQtdMotocicleta() {
		return qtdMotocicleta;
	}
	public void setQtdMotocicleta(int qtdMotocicleta) {
		this.qtdMotocicleta = qtdMotocicleta;
	}
	public int getQtdMaquinaLavar() {
		return qtdMaquinaLavar;
	}
	public void setQtdMaquinaLavar(int qtdMaquinaLavar) {
		this.qtdMaquinaLavar = qtdMaquinaLavar;
	}
	public int getQtdDvdVideocassete() {
		return qtdDvdVideocassete;
	}
	public void setQtdDvdVideocassete(int qtdDvdVideocassete) {
		this.qtdDvdVideocassete = qtdDvdVideocassete;
	}
	public int getQtdGeladeira() {
		return qtdGeladeira;
	}
	public void setQtdGeladeira(int qtdGeladeira) {
		this.qtdGeladeira = qtdGeladeira;
	}
	public int getQtdFreezer() {
		return qtdFreezer;
	}
	public void setQtdFreezer(int qtdFreezer) {
		this.qtdFreezer = qtdFreezer;
	}
	public int getQtdTelefoneFixo() {
		return qtdTelefoneFixo;
	}
	public void setQtdTelefoneFixo(int qtdTelefoneFixo) {
		this.qtdTelefoneFixo = qtdTelefoneFixo;
	}
	public int getQtdCelularResidentes() {
		return qtdCelularResidentes;
	}
	public void setQtdCelularResidentes(int qtdCelularResidentes) {
		this.qtdCelularResidentes = qtdCelularResidentes;
	}
	public int getQtdComputador() {
		return qtdComputador;
	}
	public void setQtdComputador(int qtdComputador) {
		this.qtdComputador = qtdComputador;
	}
	public int getQtdFogaoGas() {
		return qtdFogaoGas;
	}
	public void setQtdFogaoGas(int qtdFogaoGas) {
		this.qtdFogaoGas = qtdFogaoGas;
	}
	public int getQtdMaquinaCostura() {
		return qtdMaquinaCostura;
	}
	public void setQtdMaquinaCostura(int qtdMaquinaCostura) {
		this.qtdMaquinaCostura = qtdMaquinaCostura;
	}
	public int getQtdComodosSemBanheiro() {
		return qtdComodosSemBanheiro;
	}
	public void setQtdComodosSemBanheiro(int qtdComodosSemBanheiro) {
		this.qtdComodosSemBanheiro = qtdComodosSemBanheiro;
	}
	public int getQtdBanheiros() {
		return qtdBanheiros;
	}
	public void setQtdBanheiros(int qtdBanheiros) {
		this.qtdBanheiros = qtdBanheiros;
	}
	public int getQtdEmpregadosDomesticos() {
		return qtdEmpregadosDomesticos;
	}
	public void setQtdEmpregadosDomesticos(int qtdEmpregadosDomesticos) {
		this.qtdEmpregadosDomesticos = qtdEmpregadosDomesticos;
	}
	public int getTotalMembrosFamilia() {
		return totalMembrosFamilia;
	}
	public void setTotalMembrosFamilia(int totalMembrosFamilia) {
		this.totalMembrosFamilia = totalMembrosFamilia;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public GrauParentesco getParentesco() {
		return parentesco;
	}
	public void setParentesco(GrauParentesco parentesco) {
		this.parentesco = parentesco;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getAtividade() {
		return atividade;
	}
	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}
	public float getRenda() {
		return renda;
	}
	public void setRenda(float renda) {
		this.renda = renda;
	}
	public float getRendaTotalFamilia() {
		return rendaTotalFamilia;
	}
	public void setRendaTotalFamilia(float rendaTotalFamilia) {
		this.rendaTotalFamilia = rendaTotalFamilia;
	}
	public HorarioDisponivel getHorariodisponivelBolsa() {
		return horariodisponivelBolsa;
	}
	public void setHorariodisponivelBolsa(HorarioDisponivel horariodisponivelBolsa) {
		this.horariodisponivelBolsa = horariodisponivelBolsa;
	}
	public String getJustificativaPedido() {
		return justificativaPedido;
	}
	public void setJustificativaPedido(String justificativaPedido) {
		this.justificativaPedido = justificativaPedido;
	}
	
	public String getTelefoneFixoFamilia() {
		return telefoneFixoFamilia;
	}
	public void setTelefoneFixoFamilia(String telefoneFixoFamilia) {
		this.telefoneFixoFamilia = telefoneFixoFamilia;
	}
	public String getTelefoneCelularFamilia() {
		return telefoneCelularFamilia;
	}
	public void setTelefoneCelularFamilia(String telefoneCelularFamilia) {
		this.telefoneCelularFamilia = telefoneCelularFamilia;
	}
	@Override
	public String toString() {
		return "QuestionarioIniciacaoAcademica [id=" + id + ", selecaoBolsa="
				+ selecaoBolsa + ", pessoas=" + pessoas + ", enderecoAtual="
				+ enderecoAtual + ", numero=" + numero + ", complemento="
				+ complemento + ", bairro=" + bairro + ", uf=" + uf + ", cep="
				+ cep + ", cidade=" + cidade + ", pontoReferencia="
				+ pontoReferencia + ", telefoneFixo=" + telefoneFixo
				+ ", telefoneCelular=" + telefoneCelular + ", email=" + email
				+ ", enderecoFamilia=" + enderecoFamilia + ", numeroFamilia="
				+ numeroFamilia + ", bairroFamilia=" + bairroFamilia
				+ ", ufFamilia=" + ufFamilia + ", complementoFamilia="
				+ complementoFamilia + ", cepFamilia=" + cepFamilia
				+ ", cidadeFamilia=" + cidadeFamilia
				+ ", pontoReferenciaFamilia=" + pontoReferenciaFamilia
				+ ", anosEstudoPrivado=" + anosEstudoPrivado
				+ ", nivelInstrucaoMae=" + nivelInstrucaoMae
				+ ", nivelInstrucaoPai=" + nivelInstrucaoPai
				+ ", resideAtualmente=" + resideAtualmente

				+ ", definicaoLocalAtual=" + definicaoLocalAtual + ", estado="
				+ estado + ", estadoFamilia=" + estadoFamilia
				+ ", situacaoResidencia=" + situacaoResidencia
				+ ", definicaoLocalAtual=" + definicaoLocalAtual
				+ ", situacaoResidencia=" + situacaoResidencia + ", estado="
				+ estado + ", estadoFamilia=" + estadoFamilia
				+ ", qtdAparelhoSom=" + qtdAparelhoSom + ", qtdTelevisao="
				+ qtdTelevisao + ", qtdRadio=" + qtdRadio + ", qtdAutomovel="
				+ qtdAutomovel + ", qtdMotocicleta=" + qtdMotocicleta
				+ ", qtdMaquinaLavar=" + qtdMaquinaLavar
				+ ", qtdDvdVideocassete=" + qtdDvdVideocassete
				+ ", qtdGeladeira=" + qtdGeladeira + ", qtdFreezer="
				+ qtdFreezer + ", qtdTelefoneFixo=" + qtdTelefoneFixo
				+ ", qtdCelularResidentes=" + qtdCelularResidentes
				+ ", qtdComputador=" + qtdComputador + ", qtdFogaoGas="
				+ qtdFogaoGas + ", qtdMaquinaCostura=" + qtdMaquinaCostura
				+ ", qtdComodosSemBanheiro=" + qtdComodosSemBanheiro
				+ ", qtdBanheiros=" + qtdBanheiros
				+ ", qtdEmpregadosDomesticos=" + qtdEmpregadosDomesticos
				+ ", totalMembrosFamilia=" + totalMembrosFamilia + ", nome="
				+ nome + ", parentesco=" + parentesco + ", idade=" + idade
				+ ", atividade=" + atividade + ", renda=" + renda
				+ ", rendaTotalFamilia=" + rendaTotalFamilia
				+ ", horariodisponivelBolsa=" + horariodisponivelBolsa
				+ ", justificativaPedido=" + justificativaPedido
				+ ", telefoneFixoFamilia=" + telefoneFixoFamilia
				+ ", telefoneCelularFamilia=" + telefoneCelularFamilia + "]";
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
