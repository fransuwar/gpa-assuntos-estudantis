package br.com.ufc.quixada.npi.gpa.model;

import java.sql.Date;
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
	private String reside_Atualmente;
	
	@NotNull
	@Size(min=1,message="Preenchimento Obrigatório")
	private String definicao_Local_Atual;
	
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
	
	
	private int qtd_aparelho_som;
	private int qtd_televisao;
	private int qtd_radio;
	private int qtd_automovel;
	private int qtd_motocicleta;
	private int qtd_maquina_lavar;
	private int qtd_dvd_videocassete;
	private int qtd_geladeira;
	private int qtd_freezer;
	private int qtd_telefone_fixo;
	private int qtd_celular_residentes;
	private int qtd_computador;
	private int qtd_fogao_gas;
	private int qtd_maquina_costura;
	private int qtd_comodos_sem_banheiro;
	private int qtd_banheiros;
	private int qtd_empregados_domesticos;
	private int total_membros_familia;
	
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
	private String campus_preferido;
	private Date data_inscricao;
	
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
	
	
	public String getReside_Atualmente() {
		return reside_Atualmente;
	}
	public void setReside_Atualmente(String reside_Atualmente) {
		this.reside_Atualmente = reside_Atualmente;
	}
	public String getDefinicao_Local_Atual() {
		return definicao_Local_Atual;
	}
	public void setDefinicao_Local_Atual(String definicao_Local_Atual) {
		this.definicao_Local_Atual = definicao_Local_Atual;
	}
	public int getQtd_aparelho_som() {
		return qtd_aparelho_som;
	}
	public void setQtd_aparelho_som(int qtd_aparelho_som) {
		this.qtd_aparelho_som = qtd_aparelho_som;
	}
	public int getQtd_televisao() {
		return qtd_televisao;
	}
	public void setQtd_televisao(int qtd_televisao) {
		this.qtd_televisao = qtd_televisao;
	}
	public int getQtd_radio() {
		return qtd_radio;
	}
	public void setQtd_radio(int qtd_radio) {
		this.qtd_radio = qtd_radio;
	}
	public int getQtd_automovel() {
		return qtd_automovel;
	}
	public void setQtd_automovel(int qtd_automovel) {
		this.qtd_automovel = qtd_automovel;
	}
	public int getQtd_motocicleta() {
		return qtd_motocicleta;
	}
	public void setQtd_motocicleta(int qtd_motocicleta) {
		this.qtd_motocicleta = qtd_motocicleta;
	}
	public int getQtd_maquina_lavar() {
		return qtd_maquina_lavar;
	}
	public void setQtd_maquina_lavar(int qtd_maquina_lavar) {
		this.qtd_maquina_lavar = qtd_maquina_lavar;
	}
	public int getQtd_dvd_videocassete() {
		return qtd_dvd_videocassete;
	}
	public void setQtd_dvd_videocassete(int qtd_dvd_videocassete) {
		this.qtd_dvd_videocassete = qtd_dvd_videocassete;
	}
	public int getQtd_geladeira() {
		return qtd_geladeira;
	}
	public void setQtd_geladeira(int qtd_geladeira) {
		this.qtd_geladeira = qtd_geladeira;
	}
	public int getQtd_freezer() {
		return qtd_freezer;
	}
	public void setQtd_freezer(int qtd_freezer) {
		this.qtd_freezer = qtd_freezer;
	}
	public int getQtd_telefone_fixo() {
		return qtd_telefone_fixo;
	}
	public void setQtd_telefone_fixo(int qtd_telefone_fixo) {
		this.qtd_telefone_fixo = qtd_telefone_fixo;
	}
	public int getQtd_celular_residentes() {
		return qtd_celular_residentes;
	}
	public void setQtd_celular_residentes(int qtd_celular_residentes) {
		this.qtd_celular_residentes = qtd_celular_residentes;
	}
	public int getQtd_computador() {
		return qtd_computador;
	}
	public void setQtd_computador(int qtd_computador) {
		this.qtd_computador = qtd_computador;
	}
	public int getQtd_fogao_gas() {
		return qtd_fogao_gas;
	}
	public void setQtd_fogao_gas(int qtd_fogao_gas) {
		this.qtd_fogao_gas = qtd_fogao_gas;
	}
	public int getQtd_maquina_costura() {
		return qtd_maquina_costura;
	}
	public void setQtd_maquina_costura(int qtd_maquina_costura) {
		this.qtd_maquina_costura = qtd_maquina_costura;
	}
	public int getQtd_comodos_sem_banheiro() {
		return qtd_comodos_sem_banheiro;
	}
	public void setQtd_comodos_sem_banheiro(int qtd_comodos_sem_banheiro) {
		this.qtd_comodos_sem_banheiro = qtd_comodos_sem_banheiro;
	}
	public int getQtd_banheiros() {
		return qtd_banheiros;
	}
	public void setQtd_banheiros(int qtd_banheiros) {
		this.qtd_banheiros = qtd_banheiros;
	}
	public int getQtd_empregados_domesticos() {
		return qtd_empregados_domesticos;
	}
	public void setQtd_empregados_domesticos(int qtd_empregados_domesticos) {
		this.qtd_empregados_domesticos = qtd_empregados_domesticos;
	}
	public int getTotal_membros_familia() {
		return total_membros_familia;
	}
	public void setTotal_membros_familia(int total_membros_familia) {
		this.total_membros_familia = total_membros_familia;
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
		return reside_Atualmente;
	}
	public void setResideAtualmente(String resideAtualmente) {
		this.reside_Atualmente = resideAtualmente;
	}
	public String getDefinicaoLocalAtual() {
		return definicao_Local_Atual;
	}
	public void setDefinicaoLocalAtual(String definicaoLocalAtual) {
		this.definicao_Local_Atual = definicaoLocalAtual;
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
		return qtd_aparelho_som;
	}
	public void setQtdAparelhoSom(int qtdAparelhoSom) {
		this.qtd_aparelho_som = qtdAparelhoSom;
	}
	
	public String getCampus_preferido() {
		return campus_preferido;
	}
	public void setCampus_preferido(String campus_preferido) {
		this.campus_preferido = campus_preferido;
	}

	public Date getData_inscrcao() {
		return data_inscricao;
	}
	public void setData_inscrcao(Date data_inscrcao) {
		this.data_inscricao = data_inscrcao;
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
				+ ", resideAtualmente=" + reside_Atualmente

				+ ", definicaoLocalAtual=" + definicao_Local_Atual + ", estado="
				+ estado + ", estadoFamilia=" + estadoFamilia
				+ ", situacaoResidencia=" + situacaoResidencia
				+ ", definicaoLocalAtual=" + definicao_Local_Atual
				+ ", situacaoResidencia=" + situacaoResidencia + ", estado="
				+ estado + ", estadoFamilia=" + estadoFamilia
				+ ", qtdAparelhoSom=" + qtd_aparelho_som + ", qtdTelevisao="
				+ qtd_televisao + ", qtdRadio=" + qtd_radio + ", qtdAutomovel="
				+ qtd_automovel + ", qtdMotocicleta=" + qtd_motocicleta
				+ ", qtdMaquinaLavar=" + qtd_maquina_lavar
				+ ", qtdDvdVideocassete=" + qtd_dvd_videocassete
				+ ", qtdGeladeira=" + qtd_geladeira + ", qtdFreezer="
				+ qtd_freezer + ", qtdTelefoneFixo=" + qtd_telefone_fixo
				+ ", qtdCelularResidentes=" + qtd_celular_residentes
				+ ", qtdComputador=" + qtd_computador + ", qtdFogaoGas="
				+ qtd_fogao_gas + ", qtdMaquinaCostura=" + qtd_maquina_costura
				+ ", qtdComodosSemBanheiro=" + qtd_comodos_sem_banheiro
				+ ", qtdBanheiros=" + qtd_banheiros
				+ ", qtdEmpregadosDomesticos=" + qtd_empregados_domesticos
				+ ", totalMembrosFamilia=" + total_membros_familia + ", nome="
				+ nome + ", parentesco=" + parentesco + ", idade=" + idade
				+ ", atividade=" + atividade + ", renda=" + renda
				+ ", rendaTotalFamilia=" + rendaTotalFamilia
				+ ", horariodisponivelBolsa=" + horariodisponivelBolsa
				+ ", justificativaPedido=" + justificativaPedido
				+ ", telefoneFixoFamilia=" + telefoneFixoFamilia
				+ ", telefoneCelularFamilia=" + telefoneCelularFamilia + "]";
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
