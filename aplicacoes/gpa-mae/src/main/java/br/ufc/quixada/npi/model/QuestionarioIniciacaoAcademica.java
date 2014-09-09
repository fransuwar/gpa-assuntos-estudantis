package br.ufc.quixada.npi.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class QuestionarioIniciacaoAcademica {

	public QuestionarioIniciacaoAcademica(){}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Bolsa bolsa;
	
	@Column(nullable = false)
	@OneToMany(mappedBy="iniciacaoAcademica")
	private List<PessoaFamilia> pessoas;
	
	private String endereco_atual;
	private int numero;
	private String complemento;
	private String bairro;
	private enum UF{
		AC("Acre"), AL("Alagoas"), AP("Amapá"), AM("Amazonas"), BA("Bahia"),CE("Ceará"), DF("Distrito Federal"), ES("Espírito Santo"), GO("Goiás"),
		MA("Maranhão"), MT("Mato Grosso"), MS("Mato Grosso do Sul"), MG("Minas"), PA("Pará"), PB("Paraíba"), PR("Paraná"), PE("Pernambuco"),
		PI("Piauí"),RR("Roraima"), RO("Rondônia"), RJ("Rio de Janeiro"), RN("Rio Grande do Norte"), RS("Rio grande do Sul"), SC("Santa Catarina"), SP("São Paulo"), SE("Sergipe"),TO("Tocantins");
		private String nome;
		UF(String nome){
			this.nome = nome;
		}
		public String getNome(){
			
			return nome;
		}
		
	}
	private UF uf_geral;
	private String cep;
	
	private String cidade;
	private String ponto_referencia;
	private String telefone_fixo;
	private String telefone_celular;
	private String email;
	
	private String endereco_familia;
	private int numero_familia;
	private String bairro_familia;
	private String uf_familia;
	private String complemento_familia;
	private String cep_familia;
	private String cidade_familia;
	private String ponto_referencia_familia;
	
	private int anos_estudo_privado;
	
	public enum NivelInstrucao{

		EnsinoFundamentalCompleto("Ensino Fundamental Completo"), EnsinoMedioCompleto("Ensino Médio Completo"), EnsinoSuperiorCompleto("Ensino Superior Completo"), 
		EnsinoFundamentalIncompleto("Ensino Fundamental Incompleto"), EnsinoMedioIncompleto("Ensino Médio Incompleto"), EnsinoSuperiorIncompleto("Ensino Superior Incompleto");
		private String tipo;
		NivelInstrucao(String tipo){
			this.tipo = tipo;
		}
		
		public String getNome() {
			return tipo;
		}
	}
	
	private NivelInstrucao nivel_instrucao_mae;
	private NivelInstrucao nivel_instrucao_pai;
	private String reside_atualmente;
	private String definicao_local_atual;
	
	public enum SituacaoResidencia{
		cedido("Cedido"), proprio("Próprio"), alugado("Alugado"), doado("Doado");
		SituacaoResidencia(String nome){}
	}
	
	private SituacaoResidencia situacao_residencia;
	private int qtd_aparelho_som;
	private int qtd_televisao;
	private int qtd_radio;
	private int qtd_automovel;
	private int qtd_motocicleta;
	private int qtd_maquina_lavar;
	private int qtd_dvd_videocassete;
	private int qtd_geladeira;
	private int qtd_freezer;
	private int qtd_telefonefixo;
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
	private float renda_total_familia;
	private String horario_disponivel_bolsa;
	private String campus_preferido;
	private int justificativa_pedido;
	private Date data_inscricao;
	
	
	public GrauParentesco getParentesco() {
		return parentesco;
	}
	public void setParentesco(GrauParentesco parentesco) {
		this.parentesco = parentesco;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEndereco_atual() {
		return endereco_atual;
	}
	public void setEndereco_atual(String endereco_atual) {
		this.endereco_atual = endereco_atual;
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
	public UF getUf_geral() {
		return uf_geral;
	}
	public void setUf_geral(UF uf_geral) {
		this.uf_geral = uf_geral;
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
	public String getPonto_referencia() {
		return ponto_referencia;
	}
	public void setPonto_referencia(String ponto_referencia) {
		this.ponto_referencia = ponto_referencia;
	}
	public String getTelefone_fixo() {
		return telefone_fixo;
	}
	public void setTelefone_fixo(String telefone_fixo) {
		this.telefone_fixo = telefone_fixo;
	}
	public String getTelefone_celular() {
		return telefone_celular;
	}
	public void setTelefone_celular(String telefone_celular) {
		this.telefone_celular = telefone_celular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEndereco_familia() {
		return endereco_familia;
	}
	public void setEndereco_familia(String endereco_familia) {
		this.endereco_familia = endereco_familia;
	}
	public int getNumero_familia() {
		return numero_familia;
	}
	public void setNumero_familia(int numero_familia) {
		this.numero_familia = numero_familia;
	}
	public String getBairro_familia() {
		return bairro_familia;
	}
	public void setBairro_familia(String bairro_familia) {
		this.bairro_familia = bairro_familia;
	}
	public String getUf_familia() {
		return uf_familia;
	}
	public void setUf_familia(String uf_familia) {
		this.uf_familia = uf_familia;
	}
	public String getComplemento_familia() {
		return complemento_familia;
	}
	public void setComplemento_familia(String complemento_familia) {
		this.complemento_familia = complemento_familia;
	}
	public String getCep_familia() {
		return cep_familia;
	}
	public void setCep_familia(String cep_familia) {
		this.cep_familia = cep_familia;
	}
	public String getCidade_familia() {
		return cidade_familia;
	}
	public void setCidade_familia(String cidade_familia) {
		this.cidade_familia = cidade_familia;
	}
	public String getPonto_referencia_familia() {
		return ponto_referencia_familia;
	}
	public void setPonto_referencia_familia(String ponto_referencia_familia) {
		this.ponto_referencia_familia = ponto_referencia_familia;
	}
	public int getAnos_estudo_privado() {
		return anos_estudo_privado;
	}
	public void setAnos_estudo_privado(int anos_estudo_privado) {
		this.anos_estudo_privado = anos_estudo_privado;
	}
	public NivelInstrucao getNivel_instrucao_mae() {
		return nivel_instrucao_mae;
	}
	public void setNivel_instrucao_mae(NivelInstrucao nivel_instrucao_mae) {
		this.nivel_instrucao_mae = nivel_instrucao_mae;
	}
	public NivelInstrucao getNivel_instrucao_pai() {
		return nivel_instrucao_pai;
	}
	public void setNivel_instrucao_pai(NivelInstrucao nivel_instrucao_pai) {
		this.nivel_instrucao_pai = nivel_instrucao_pai;
	}
	public String getReside_atualmente() {
		return reside_atualmente;
	}
	public void setReside_atualmente(String reside_atualmente) {
		this.reside_atualmente = reside_atualmente;
	}
	public String getDefinicao_local_atual() {
		return definicao_local_atual;
	}
	public void setDefinicao_local_atual(String definicao_local_atual) {
		this.definicao_local_atual = definicao_local_atual;
	}
	public SituacaoResidencia getSituacao_residencia() {
		return situacao_residencia;
	}
	public void setSituacao_residencia(SituacaoResidencia situacao_residencia) {
		this.situacao_residencia = situacao_residencia;
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
	public int getQtd_telefonefixo() {
		return qtd_telefonefixo;
	}
	public void setQtd_telefonefixo(int qtd_telefonefixo) {
		this.qtd_telefonefixo = qtd_telefonefixo;
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
		return renda_total_familia;
	}
	public void setRenda_total_familia(float renda_total_familia) {
		this.renda_total_familia = renda_total_familia;
	}
	public String getHorario_disponivel_bolsa() {
		return horario_disponivel_bolsa;
	}
	public void setHorario_disponivel_bolsa(String horario_disponivel_bolsa) {
		this.horario_disponivel_bolsa = horario_disponivel_bolsa;
	}
	public String getCampus_preferido() {
		return campus_preferido;
	}
	public void setCampus_preferido(String campus_preferido) {
		this.campus_preferido = campus_preferido;
	}
	public int getJustificativa_pedido() {
		return justificativa_pedido;
	}
	public void setJustificativa_pedido(int justificativa_pedido) {
		this.justificativa_pedido = justificativa_pedido;
	}
	public Date getData_inscricao() {
		return data_inscricao;
	}
	public void setData_inscricao(Date data_inscricao) {
		this.data_inscricao = data_inscricao;
	}
	public Bolsa getBolsa() {
		return bolsa;
	}
	public void setBolsa(Bolsa bolsa) {
		this.bolsa = bolsa;
	}
	public List<PessoaFamilia> getPessoas() {
		return pessoas;
	}
	public void setPessoas(List<PessoaFamilia> pessoas) {
		this.pessoas = pessoas;
	}
	@Override
	public String toString() {
		return "QuestionarioIniciacaoAcademica [id=" + id + ", bolsa=" + bolsa
				+ ", pessoas=" + pessoas + ", endereco_atual=" + endereco_atual
				+ ", numero=" + numero + ", complemento=" + complemento
				+ ", bairro=" + bairro + ", uf=" + uf_geral + ", cep=" + cep
				+ ", cidade=" + cidade + ", ponto_referencia="
				+ ponto_referencia + ", telefone_fixo=" + telefone_fixo
				+ ", telefone_celular=" + telefone_celular + ", email=" + email
				+ ", endereco_familia=" + endereco_familia
				+ ", numero_familia=" + numero_familia + ", bairro_familia="
				+ bairro_familia + ", uf_familia=" + uf_familia
				+ ", complemento_familia=" + complemento_familia
				+ ", cep_familia=" + cep_familia + ", cidade_familia="
				+ cidade_familia + ", ponto_referencia_familia="
				+ ponto_referencia_familia + ", anos_estudo_privado="
				+ anos_estudo_privado + ", nivel_instrucao_mae="
				+ nivel_instrucao_mae + ", nivel_instrucao_pai="
				+ nivel_instrucao_pai + ", reside_atualmente="
				+ reside_atualmente + ", definicao_local_atual="
				+ definicao_local_atual + ", situacao_residencia="
				+ situacao_residencia + ", qtd_aparelho_som="
				+ qtd_aparelho_som + ", qtd_televisao=" + qtd_televisao
				+ ", qtd_radio=" + qtd_radio + ", qtd_automovel="
				+ qtd_automovel + ", qtd_motocicleta=" + qtd_motocicleta
				+ ", qtd_maquina_lavar=" + qtd_maquina_lavar
				+ ", qtd_dvd_videocassete=" + qtd_dvd_videocassete
				+ ", qtd_geladeira=" + qtd_geladeira + ", qtd_freezer="
				+ qtd_freezer + ", qtd_telefonefixo=" + qtd_telefonefixo
				+ ", qtd_celular_residentes=" + qtd_celular_residentes
				+ ", qtd_computador=" + qtd_computador + ", qtd_fogao_gas="
				+ qtd_fogao_gas + ", qtd_maquina_costura="
				+ qtd_maquina_costura + ", qtd_comodos_sem_banheiro="
				+ qtd_comodos_sem_banheiro + ", qtd_banheiros=" + qtd_banheiros
				+ ", qtd_empregados_domesticos=" + qtd_empregados_domesticos
				+ ", total_membros_familia=" + total_membros_familia
				+ ", nome=" + nome + ", parentesco=" + parentesco + ", idade="
				+ idade + ", atividade=" + atividade + ", renda=" + renda
				+ ", renda_total_familia=" + renda_total_familia
				+ ", horario_disponivel_bolsa=" + horario_disponivel_bolsa
				+ ", campus_preferido=" + campus_preferido
				+ ", justificativa_pedido=" + justificativa_pedido
				+ ", data_inscricao=" + data_inscricao + "]";
	}
	
	
	
	
	
}
