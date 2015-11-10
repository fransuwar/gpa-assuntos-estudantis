package br.ufc.quixada.npi.gpa.model;

import java.util.Date;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.ufc.quixada.npi.gpa.enums.Estado;
import br.ufc.quixada.npi.gpa.enums.GrauParentesco;
import br.ufc.quixada.npi.gpa.enums.NivelInstrucao;
import br.ufc.quixada.npi.gpa.enums.SituacaoResidencia;

@Entity																	
@NamedQueries({ @NamedQuery(name = "IniAcad.findIniAcadById", query = "SELECT DISTINCT am FROM QuestionarioIniciacaoAcademica am WHERE am.aluno.id = :idAluno") })
public class QuestionarioIniciacaoAcademica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private SelecaoBolsa selecaoBolsa;

	@ManyToOne(cascade = CascadeType.MERGE)
	private Aluno aluno;

	@NotNull(message = "Campo Obrigatório")
	private Integer qtdAparelhoSom;
	@NotNull(message = "Campo Obrigatório")
	private Integer qtdTelevisao;
	@NotNull(message = "Campo Obrigatório")
	private Integer qtdRadio;
	@NotNull(message = "Campo Obrigatório")
	private Integer qtdAutomovel;
	@NotNull(message = "Campo Obrigatório")
	private Integer qtdMotocicleta;
	@NotNull(message = "Campo Obrigatório")
	private Integer qtdMaquinaLavar;
	@NotNull(message = "Campo Obrigatório")
	private Integer qtdDvdVideocassete;
	@NotNull(message = "Campo Obrigatório")
	private Integer qtdGeladeira;
	@NotNull(message = "Campo Obrigatório")
	private Integer qtdFreezer;
	@NotNull(message = "Campo Obrigatório")
	private Integer qtdTelefoneFixo;
	@NotNull(message = "Campo Obrigatório")
	private Integer qtdCelularResidentes;
	@NotNull(message = "Campo Obrigatório")
	private Integer qtdComputador;
	@NotNull(message = "Campo Obrigatório")
	private Integer qtdFogaoGas;
	@NotNull(message = "Campo Obrigatório")
	private Integer qtdMaquinaCostura;
	@NotNull(message = "Campo Obrigatório")
	private Integer qtdComodosSemBanheiro;
	@NotNull(message = "Campo Obrigatório")
	private Integer qtdBanheiros;
	@NotNull(message = "Campo Obrigatório")
	private Integer qtdEmpregadosDomesticos;
	@NotNull(message = "Campo Obrigatório")
	private Integer totalMembrosFamilia;

	private String nome;
	private int idade;
	private String atividade;
	private float renda;
	private float rendaTotalFamilia;

	@Column(nullable = false)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "iniciacaoacademica_id")
	@NotEmpty(message = "Campo obrigatório")
	private List<PessoaFamilia> pessoas;

	@Column(nullable = false)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "questionarioiniciacaoacademica_id")
	private List<HorarioDisponivel> horariosDisponiveisBolsa;

	@NotNull
	@Size(min = 5, message = "Campo Obrigatório")
	private String enderecoAtual;

	@NotNull(message = "Campo Obrigatório")
	private Integer numero;

	private String complemento;

	@NotNull
	@Size(min = 1, message = "Campo Obrigatório")
	private String bairro;
	
	@NotEmpty(message = "Campo Obrigatório")
	private String uf;
	
	@NotEmpty(message = "Campo Obrigatório")
	private String cep;

	@NotNull
	@Size(min = 3, message = "Campo Obrigatório")
	private String cidade;

	private String pontoReferencia;

	private String telefoneFixo;

	private String telefoneCelular;

	private String email;

	@NotNull
	@Size(min = 1, message = "Campo Obrigatório")
	private String enderecoFamilia;
	@NotEmpty(message = "Campo obrigatório")
	@NotNull(message = "Campo Obrigatório")
	private Integer numeroFamilia;

	@NotNull
	@Size(min = 1, message = "Campo Obrigatório")
	private String bairroFamilia;

	private String ufFamilia;

	private String complementoFamilia;

	private String cepFamilia;

	@NotNull
	@Size(min = 3, message = "Campo Obrigatório")
	private String cidadeFamilia;

	private String pontoReferenciaFamilia;

	private int anosEstudoPrivado;

	@Enumerated(EnumType.STRING)
	private NivelInstrucao nivelInstrucaoMae;

	@Enumerated(EnumType.STRING)
	private NivelInstrucao nivelInstrucaoPai;

	@NotNull
	@Size(min = 1, message = "Campo Obrigatório")
	private String resideAtualmente;

	@NotNull
	@Size(min = 1, message = "Campo Obrigatório")
	private String definicaoLocalAtual;

	@Enumerated(EnumType.STRING)
	private Estado estado;

	@Enumerated(EnumType.STRING)
	private Estado estadoFamilia;

	@Enumerated(EnumType.STRING)
	private SituacaoResidencia situacaoResidencia;

	private String campusPreferido;

	private Date dataInscricao;

	@Enumerated(EnumType.STRING)
	private GrauParentesco parentesco;

	@NotNull
	@Size(min = 1, message = "Campo Obrigatório")
	private String justificativaPedido;

	private String telefoneFixoFamilia;

	private String telefoneCelularFamilia;

	public QuestionarioIniciacaoAcademica() {

	}
	
	public Aluno getAluno() {
		return aluno;
	}
	
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Integer getQtdAparelhoSom() {
		return qtdAparelhoSom;
	}

	public void setQtdAparelhoSom(Integer qtdAparelhoSom) {
		this.qtdAparelhoSom = qtdAparelhoSom;
	}

	public Integer getQtdTelevisao() {
		return qtdTelevisao;
	}

	public void setQtdTelevisao(Integer qtdTelevisao) {
		this.qtdTelevisao = qtdTelevisao;
	}

	public Integer getQtdRadio() {
		return qtdRadio;
	}

	public void setQtdRadio(Integer qtdRadio) {
		this.qtdRadio = qtdRadio;
	}

	public Integer getQtdAutomovel() {
		return qtdAutomovel;
	}

	public void setQtdAutomovel(Integer qtdAutomovel) {
		this.qtdAutomovel = qtdAutomovel;
	}

	public Integer getQtdMotocicleta() {
		return qtdMotocicleta;
	}

	public void setQtdMotocicleta(Integer qtdMotocicleta) {
		this.qtdMotocicleta = qtdMotocicleta;
	}

	public Integer getQtdMaquinaLavar() {
		return qtdMaquinaLavar;
	}

	public void setQtdMaquinaLavar(Integer qtdMaquinaLavar) {
		this.qtdMaquinaLavar = qtdMaquinaLavar;
	}

	public Integer getQtdDvdVideocassete() {
		return qtdDvdVideocassete;
	}

	public void setQtdDvdVideocassete(Integer qtdDvdVideocassete) {
		this.qtdDvdVideocassete = qtdDvdVideocassete;
	}

	public Integer getQtdGeladeira() {
		return qtdGeladeira;
	}

	public void setQtdGeladeira(Integer qtdGeladeira) {
		this.qtdGeladeira = qtdGeladeira;
	}

	public Integer getQtdFreezer() {
		return qtdFreezer;
	}

	public void setQtdFreezer(Integer qtdFreezer) {
		this.qtdFreezer = qtdFreezer;
	}

	public Integer getQtdTelefoneFixo() {
		return qtdTelefoneFixo;
	}

	public void setQtdTelefoneFixo(Integer qtdTelefoneFixo) {
		this.qtdTelefoneFixo = qtdTelefoneFixo;
	}

	public Integer getQtdCelularResidentes() {
		return qtdCelularResidentes;
	}

	public void setQtdCelularResidentes(Integer qtdCelularResidentes) {
		this.qtdCelularResidentes = qtdCelularResidentes;
	}

	public Integer getQtdComputador() {
		return qtdComputador;
	}

	public void setQtdComputador(Integer qtdComputador) {
		this.qtdComputador = qtdComputador;
	}

	public Integer getQtdFogaoGas() {
		return qtdFogaoGas;
	}

	public void setQtdFogaoGas(Integer qtdFogaoGas) {
		this.qtdFogaoGas = qtdFogaoGas;
	}

	public Integer getQtdMaquinaCostura() {
		return qtdMaquinaCostura;
	}

	public void setQtdMaquinaCostura(Integer qtdMaquinaCostura) {
		this.qtdMaquinaCostura = qtdMaquinaCostura;
	}

	public Integer getQtdComodosSemBanheiro() {
		return qtdComodosSemBanheiro;
	}

	public void setQtdComodosSemBanheiro(Integer qtdComodosSemBanheiro) {
		this.qtdComodosSemBanheiro = qtdComodosSemBanheiro;
	}

	public Integer getQtdBanheiros() {
		return qtdBanheiros;
	}

	public void setQtdBanheiros(Integer qtdBanheiros) {
		this.qtdBanheiros = qtdBanheiros;
	}

	public Integer getQtdEmpregadosDomesticos() {
		return qtdEmpregadosDomesticos;
	}

	public void setQtdEmpregadosDomesticos(Integer qtdEmpregadosDomesticos) {
		this.qtdEmpregadosDomesticos = qtdEmpregadosDomesticos;
	}

	public Integer getTotalMembrosFamilia() {
		return totalMembrosFamilia;
	}

	public void setTotalMembrosFamilia(Integer totalMembrosFamilia) {
		this.totalMembrosFamilia = totalMembrosFamilia;
	}

	public String getCampusPreferido() {
		return campusPreferido;
	}

	public void setCampusPreferido(String campusPreferido) {
		this.campusPreferido = campusPreferido;
	}

	public Date getDataInscricao() {
		return dataInscricao;
	}

	public void setDataInscricao(Date dataInscricao) {
		this.dataInscricao = dataInscricao;
	}

	public float getRendaTotalFamilia() {
		return rendaTotalFamilia;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SelecaoBolsa getSelecaoBolsa() {
		return selecaoBolsa;
	}

	public void setSelecaoBolsa(SelecaoBolsa selecaoBolsa) {
		this.selecaoBolsa = selecaoBolsa;
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

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
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

	public Integer getNumeroFamilia() {
		return numeroFamilia;
	}

	public void setNumeroFamilia(Integer numeroFamilia) {
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

	public float getRenda_total_familia() {
		return rendaTotalFamilia;
	}

	public void setRendaTotalFamilia(float rendaTotalFamilia) {
		this.rendaTotalFamilia = rendaTotalFamilia;
	}

	public List<HorarioDisponivel> getHorariosDisponiveisBolsa() {
		return horariosDisponiveisBolsa;
	}

	public void setHorariosDisponiveisBolsa(
			List<HorarioDisponivel> horariosDisponiveisBolsa) {
		this.horariosDisponiveisBolsa = horariosDisponiveisBolsa;
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

	public String toString() {
		return "QuestionarioIniciacaoAcademica [id=" + id + "]";
	}

}
