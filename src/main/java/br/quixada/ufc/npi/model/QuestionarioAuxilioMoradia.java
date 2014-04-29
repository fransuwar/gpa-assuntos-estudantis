package br.quixada.ufc.npi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class QuestionarioAuxilioMoradia {
	
	public QuestionarioAuxilioMoradia(){
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)  
	private Integer id;
	private String caminhoFoto;
	public enum MoraCom{
		Pais("Pais"), Pai("Pai"), Mae("Mãe"), Irmaos("irmãos"), Parentes("Parentes"),
		Conjuge_Companheiro("Cônjuge ou Companheiro(a)"), Filhos("Filhos(as)"), Outra_moradia("Outros");
		MoraCom(String nome) {
		
		}
	}
	private String outrosMoraCom;
	private String enderecoSedeCurso;
	private String nomeMae;
	private String nomePai;
	private String logradouro;
	private int numeroCasa;
	private String bairro;
	private String complemento;
	private String cidade;
	private String cep;
	private String pontoReferencia;
	private String telefone;
	public enum Estado {
        Acre("Acre"), Alagoas("Alagoas"), Amapa("Amapa"), Amazonas("Amazonas"), Bahia("Bahia"), Ceara("Ceará"), Distrito_Federal("Distrito Federal"), 
        Espirito_Santo("Espirito Santo"), Goias("Goiás"), Maranhao("Maranhão"), Mato_Grosso("Mato Grosso"), Mato_Grosso_do_Sul("Mato Grosso do Sul"),
        Minas_Gerais("Minas Gerais"), Para("Pará"), Paraiba("Paraíba"), Parana("Paraná"), Pernambuco("Pernambuco"), Piaui("Piauí"), 
        Rio_de_Janeiro("Rio de Janeiro"), Rio_Grande_do_Norte("Rio Grande do Norte"), Rio_Grande_do_Sul("Rio Grande do Sul"), Rondonia("Rondonia"), Roraima("Roraima"), 
        Santa_Catarina("Santa Catarina"), Sao_Paulo("São Paulo"), Sergipe("Sergipe"), Tocantins("Tocantins");
                
        Estado(String nome){}
	}
	public enum SituacaoImovel{
		Cedido("Cedido"), Alugado("Alugado"), Proprio("Próprio"), Financiado("Financiado");
		SituacaoImovel(String nome){}
	}
	private String valorMensalFinanciamento;
	private String propriedadeRural;
	
	public enum GrauParentesco{
		Filho_a("Filho(a)"), Neto("Neto(a)"), Sobrinho("Sobrinho(a)"), Irmao("Irmão"), Conjuge_Companheiro("Cônjuge ou Companheiro(a)");
		GrauParentesco(String nome){}
	}
	private double areaPropriedade;
	private String cidadeEstado;
	private String veiculos;
	
	
}
