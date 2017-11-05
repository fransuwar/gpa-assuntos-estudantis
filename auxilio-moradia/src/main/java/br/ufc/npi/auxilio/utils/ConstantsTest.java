package br.ufc.npi.auxilio.utils;

public class ConstantsTest {
	
	//Script para executar ação de click em determinado elemento
	public static final String SCRIPT_CLICK = "arguments[0].click();";
	
	//Elementos obtidos através da TAG
	public static final String TAG_TD = "td";
	public static final String TAG_DIV = "div";
	
	//Elementos obtidos através do ID
	public static final String ID_FIELD_USERNAME = "username";
	public static final String ID_FIELD_PASSWORD = "password";
	public static final String ID_FIELD_ANO = "ano";
	public static final String ID_FIELD_INICIO = "inicio";
	public static final String ID_FIELD_TERMINO = "termino";
	public static final String ID_FIELD_VAGAS = "vagas";
	public static final String ID_BUTTON_ENTRAR = "btn-entrar";
	public static final String ID_BUTTON_CADASTRAR = "btn-cadastrar";
	public static final String ID_BUTTON_SALVAR = "btn-salvar";
	public static final String ID_BUTTON_CANCELAR = "btn-cancelar";
	public static final String ID_SPAN_MENSAGEM = "mensagem";
	
	//Elementos obtidos através do CSSSELECTOR
	public static final String CSSSELECTOR_FIELD_SELECAO = "input.select-dropdown";
	
	//Elementos obtidos através do XPATH
	public static final String XPATH_FIELD_SELECAO_1 = ".//div[@class='col s4 input-field']/div/ul/li[1]/span";
	public static final String XPATH_FIELD_SELECAO_2 = ".//div[@class='col s4 input-field']/div/ul/li[2]/span";
	public static final String XPATH_FIELD_SELECAO_3 = ".//div[@class='col s4 input-field']/div/ul/li[3]/span";
	public static final String XPATH_FIELD_DATA_INICIO = ".//input[@id='inicio']";
	public static final String XPATH_FIELD_DATA_TERMINO = ".//input[@id='termino']";
	
	public static final String XPATH_SELECT_DATA_INICIO_MES = ".//select[@class='picker__select--month browser-default'][@aria-controls='inicio_table']";
	public static final String XPATH_SELECT_DATA_INICIO_ANO = ".//select[@class='picker__select--year browser-default'][@aria-controls='inicio_table']";
	public static final String XPATH_TR_TABLE_DATA_INICIO = ".//table[@id='inicio_table']/tbody/tr";
	public static final String XPATH_BUTTON_DATA_INICIO_CLEAR = ".//button[@class='btn-flat picker__clear'][@aria-controls='inicio']";
	public static final String XPATH_BUTTON_DATA_INICIO_CLOSE = ".//button[@class='btn-flat picker__close'][@aria-controls='inicio']";
	
	public static final String XPATH_SELECT_DATA_TERMINO_MES = ".//select[@class='picker__select--month browser-default'][@aria-controls='termino_table']";
	public static final String XPATH_SELECT_DATA_TERMINO_ANO = ".//select[@class='picker__select--year browser-default'][@aria-controls='termino_table']";
	public static final String XPATH_TR_TABLE_DATA_TERMINO = ".//table[@id='termino_table']/tbody/tr";
	public static final String XPATH_BUTTON_DATA_TERMINO_CLEAR = ".//button[@class='btn-flat picker__clear'][@aria-controls='termino']";
	public static final String XPATH_BUTTON_DATA_TERMINO_CLOSE = ".//button[@class='btn-flat picker__close'][@aria-controls='termino']";
	
	public static final String XPATH_BUTTON_SALVAR = ".//button[@id='btn-salvar']";
	
	
	//Títulos de páginas
	public static final String TITLE_PAGE_LISTAR = "Auxílio - Listar Seleção";
	public static final String TITLE_PAGE_VISUALIZAR = "Auxílio - Visualizar Inscrições";
	
	//URLs das páginas
	public static final String URL_PAGE_LOGIN = "http://localhost:8080/login";
	public static final String URL_PAGE_PRINCIPAL = "http://localhost:8080/selecao";
	public static final String URL_PAGE_CADASTRAR = "http://localhost:8080/selecao/cadastrar";
	public static final String URL_PAGE_LISTAR = "http://localhost:8080/selecao/listar/";
	public static final String URL_PAGE_DETALHES = "http://localhost:8080/selecao/detalhes/";
	public static final String URL_PAGE_VISUALIZAR_INSCRICAO_52 = "http://localhost:8080/selecao/inscricoes/52";
	
	//Valores utilizados para realizar o login
	public static final String LOGIN_VALUE = "11111111101";
	public static final String PASSWORD_VALUE = "1234";
	
	//Valores utilizados para selecionar o tipo de Seleção
	public static final String SELECAO_AUXILIO_MORADIA = "Auxílio Moradia";
	public static final String SELECAO_AUXILIO_EMERGENCIAL = "Auxílio Emergencial";
	public static final String SELECAO_INICIAO_ACADEMICA = "Iniciação Acadêmica";
}
