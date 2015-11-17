package br.ufc.quixada.npi.gpa.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.ufc.quixada.npi.gpa.enums.NivelInstrucao;
import br.ufc.quixada.npi.gpa.enums.SituacaoResidencia;

@Entity																	
@NamedQueries({ @NamedQuery(name = "IniAcad.findIniAcadById", query = "SELECT DISTINCT am FROM QuestionarioIniciacaoAcademica am WHERE am.aluno.id = :idAluno") })
public class QuestionarioIniciacaoAcademica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
<<<<<<< HEAD

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

	@NotEmpty(message = "Campo obrigatório")
	private String enderecoAtual;

=======
	
	private String telefoneFixo;
	
	private String telefoneCelular;
	
	private int anosEstudoPrivado;
	
	@Enumerated(EnumType.STRING)
	private NivelInstrucao nivelInstrucaoMae;
	
	@Enumerated(EnumType.STRING)
	private NivelInstrucao nivelInstrucaoPai;
	
	@NotNull
	@Size(min = 1, message = "Campo Obrigatório")
	private String justificativaPedido;
	
	@NotNull(message = "Campo Obrigatório")
	private String endereco;
	
>>>>>>> ff7b69fa2608d313f6e3609ad150ccf9e499fe9d
	@NotNull(message = "Campo Obrigatório")
	private Integer numero;
	
	private String complemento;
<<<<<<< HEAD

	@NotEmpty
	private String bairro;
	
	@NotEmpty(message = "Campo Obrigatório")
	private String cep;
<<<<<<< HEAD

	@NotEmpty
	@Size(min = 3, message = "Campo Obrigatório")
	private String cidade;

	private String pontoReferencia;

	private String telefoneFixo;

	private String telefoneCelular;

	private String email;

	@NotEmpty
	@Size(min = 1, message = "Campo Obrigatório")
	private String enderecoFamilia;
	@NotNull(message = "Campo Obrigatório")
	private Integer numeroFamilia;

	@NotEmpty
=======
	
	@NotNull
	@Size(min = 3, message = "Campo Obrigatório")
	private String cidade;
	
	@NotEmpty(message = "Campo Obrigatório")
	private String estado;
	
	private String referencia;
	
	@NotNull(message = "Campo Obrigatório")
	private String enderecoFamilia;
	
	@NotNull(message = "Campo Obrigatório")
	private Integer numeroFamilia;
	
	private String complementoFamilia;
	
	@NotNull
>>>>>>> ff7b69fa2608d313f6e3609ad150ccf9e499fe9d
	@Size(min = 1, message = "Campo Obrigatório")
	private String bairroFamilia;
	
	@NotEmpty(message = "Campo Obrigatório")
	private String cepFamilia;
<<<<<<< HEAD

	@NotEmpty
	@Size(min = 3, message = "Campo Obrigatório")
	private String cidadeFamilia;

	private String pontoReferenciaFamilia;

	private int anosEstudoPrivado;

	@Enumerated(EnumType.STRING)
	private NivelInstrucao nivelInstrucaoMae;

	@Enumerated(EnumType.STRING)
	private NivelInstrucao nivelInstrucaoPai;

	@NotEmpty
	@Size(min = 1, message = "Campo Obrigatório")
	private String resideAtualmente;

	@NotEmpty
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

	@NotEmpty
	@Size(min = 1, message = "Campo Obrigatório")
	private String justificativaPedido;

	private String telefoneFixoFamilia;

	private String telefoneCelularFamilia;

	public QuestionarioIniciacaoAcademica() {

	}
=======
	
	@NotNull
	@Size(min = 3, message = "Campo Obrigatório")
	private String cidadeFamilia;
	
	@NotEmpty(message = "Campo Obrigatório")
	private String estadoFamilia;
	
	private String referenciaFamilia;
	
	private String comQuemReside;
	
	private String tipoResidencia;
	
	@Enumerated(EnumType.STRING)
	private SituacaoResidencia situacaoResidencia;
>>>>>>> ff7b69fa2608d313f6e3609ad150ccf9e499fe9d
	
	private Integer qtdAparelhoSom;
	
	private Integer qtdTelevisao;
	
	private Integer qtdRadio;
	
	private Integer qtdAutomovel;
	
	private Integer qtdMotocicleta;
	
	private Integer qtdMaquinaLavar;
	
	private Integer qtdDvd;
	
	private Integer qtdGeladeira;
	
	private Integer qtdFreezer;
	
	private Integer qtdTelefoneFixo;
	
	private Integer qtdCelular;
	
	private Integer qtdComputador;
	
	private Integer qtdFogaoGas;
	
	private Integer qtdMaquinaDeCostura;
	
	private Integer qtdComodos;
	
	private Integer qtdBanheiros;
	
	private Integer qtdEmpregadosDomesticos;
	
	@NotEmpty(message = "Campo obrigatório")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "questionarioiniciacaoacademica_id")
	private List<HorarioDisponivel> horariosDisponiveisBolsa;
	
	@NotEmpty(message = "Campo obrigatório")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "iniciacaoacademica_id")
	private List<PessoaFamilia> pessoas;
	
	public QuestionarioIniciacaoAcademica() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getJustificativaPedido() {
		return justificativaPedido;
	}

	public void setJustificativaPedido(String justificativaPedido) {
		this.justificativaPedido = justificativaPedido;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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
<<<<<<< HEAD
	
=======

>>>>>>> ff7b69fa2608d313f6e3609ad150ccf9e499fe9d
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getEstadoFamilia() {
		return estadoFamilia;
	}

	public void setEstadoFamilia(String estadoFamilia) {
		this.estadoFamilia = estadoFamilia;
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

	public String getComplementoFamilia() {
		return complementoFamilia;
	}

	public void setComplementoFamilia(String complementoFamilia) {
		this.complementoFamilia = complementoFamilia;
	}

	public String getBairroFamilia() {
		return bairroFamilia;
	}

	public void setBairroFamilia(String bairroFamilia) {
		this.bairroFamilia = bairroFamilia;
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

	public String getReferenciaFamilia() {
		return referenciaFamilia;
	}

	public void setReferenciaFamilia(String referenciaFamilia) {
		this.referenciaFamilia = referenciaFamilia;
	}

	

	public String getComQuemReside() {
		return comQuemReside;
	}

	public void setComQuemReside(String comQuemReside) {
		this.comQuemReside = comQuemReside;
	}

	public String getTipoResidencia() {
		return tipoResidencia;
	}

	public void setTipoResidencia(String tipoResidencia) {
		this.tipoResidencia = tipoResidencia;
	}

	public SituacaoResidencia getSituacaoResidencia() {
		return situacaoResidencia;
	}

	public void setSituacaoResidencia(SituacaoResidencia situacaoResidencia) {
		this.situacaoResidencia = situacaoResidencia;
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

	public Integer getQtdDvd() {
		return qtdDvd;
	}

	public void setQtdDvd(Integer qtdDvd) {
		this.qtdDvd = qtdDvd;
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

	public Integer getQtdCelular() {
		return qtdCelular;
	}

	public void setQtdCelular(Integer qtdCelular) {
		this.qtdCelular = qtdCelular;
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

	public Integer getQtdMaquinaDeCostura() {
		return qtdMaquinaDeCostura;
	}

	public void setQtdMaquinaDeCostura(Integer qtdMaquinaDeCostura) {
		this.qtdMaquinaDeCostura = qtdMaquinaDeCostura;
	}

	public Integer getQtdComodos() {
		return qtdComodos;
	}

	public void setQtdComodos(Integer qtdComodos) {
		this.qtdComodos = qtdComodos;
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
	
	public List<HorarioDisponivel> getHorariosDisponiveisBolsa() {
		return horariosDisponiveisBolsa;
	}

	public void setHorariosDisponiveisBolsa(List<HorarioDisponivel> horariosDisponiveisBolsa) {
		this.horariosDisponiveisBolsa = horariosDisponiveisBolsa;
	}

	public List<PessoaFamilia> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<PessoaFamilia> pessoas) {
		this.pessoas = pessoas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestionarioIniciacaoAcademica other = (QuestionarioIniciacaoAcademica) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "QuestionarioIniciacaoAcademica [id=" + id + "]";
	}
	

}
