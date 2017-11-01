package br.ufc.npi.auxilio.utils;

public class ConstantsTest {
	
	//Constantes de elementos web
	public static final String ELEMENT_TITLE = "title";
	
	//Constantes elementos By.id
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
	public static final String ID_SPAN_NOME = "nome-completo";
	public static final String ID_SPAN_CPF = "cpf";
	public static final String ID_SPAN_EMAIL = "email";
			
	//Constantes elementos By.cssSelector
	public static final String CSSSELECTOR_FIELD_SELECAO = "input.select-dropdown";
	public static final String CSSSELECTOR_FIELD_DATA_MES = "select.picker__select--month.browser-default";
	public static final String CSSSELECTOR_FIELD_DATA_ANO = "select.picker__select--year.browser-default";
	
	//Constantes elementos By.xpath
	public static final String XPATH_FIELD_SELECAO_1 = ".//div[@class='col s4 input-field']/div/ul/li[1]/span";
	public static final String XPATH_FIELD_SELECAO_2 = ".//div[@class='col s4 input-field']/div/ul/li[2]/span";
	public static final String XPATH_FIELD_SELECAO_3 = ".//div[@class='col s4 input-field']/div/ul/li[3]/span";
	public static final String XPATH_FIELD_DATA_INICIO = ".//input[@id='inicio']";
	public static final String XPATH_FIELD_DATA_TERMINO = ".//input[@id='termino']";
	public static final String XPATH_SELECT_DATA_INICIO_DIA = ".//table[@id='inicio_table']/tbody/tr[4]/td[3]/div";
	public static final String XPATH_SELECT_DATA_TERMINO_DIA = ".//table[@id='inicio_table']/tbody/tr[4]/td[3]/div";
	public static final String XPATH_BUTTON_DATA_INICIO_CLEAR = ".//button[@class='btn-flat picker__clear'][@aria-controls='inicio']";
	public static final String XPATH_BUTTON_DATA_TERMINO_CLEAR = ".//button[@class='btn-flat picker__clear'][@aria-controls='termino']";
	public static final String XPATH_BUTTON_DATA_INICIO_CLOSE = ".//button[@class='btn-flat picker__close'][@aria-controls='inicio']";
	public static final String XPATH_BUTTON_DATA_TERMINO_CLOSE = ".//button[@class='btn-flat picker__close'][@aria-controls='termino']";
	public static final String XPATH_BUTTON_SALVAR = ".//button[@id='btn-salvar']";
	
	//Constantes de títulos de páginas
	public static final String TITLE_PAGE_LOGIN = "Auxílio Moradia - Login";
	public static final String TITLE_PAGE_CADASTRAR = "Auxílio - Cadastrar Seleção";
	public static final String TITLE_PAGE_DETALHES = "Auxílio - Detalhes da Seleção";
	public static final String TITLE_PAGE_LISTAR = "Auxílio - Listar Seleção";
	
	public static final String URL_PAGE_LOGIN = "http://127.0.0.1:8080/login";
	public static final String URL_PAGE_PRINCIPAL = "http://127.0.0.1:8080/selecao";
	public static final String URL_PAGE_CADASTRAR = "http://127.0.0.1:8080/selecao/cadastrar";
	public static final String URL_PAGE_LISTAR = "http://127.0.0.1:8080/selecao/listar/";
	public static final String URL_PAGE_DETALHES = "http://127.0.0.1:8080/selecao/detalhes/";
	
	public static final String LOGIN_VALUE = "11111111101";
	public static final String PASSWORD_VALUE = "1234";
	
	public static final String MENSAGEM_SUCESSO_CADASTRO = "Seleção cadastrada com sucesso";
	public static final String MENSAGEM_CAMPO_OBRIGATORIO = "Preencha todos os campos obrigatórios";
	public static final String MENSGAME_SELECAO_EXISTENTE = "Seleção já existente";
	public static final String MENSGAME_DATA_INVALIDA = "Data informada é inválida";
	
	public static final String SELECAO_AUXILIO_MORADIA = "Auxílio Moradia";
	public static final String SELECAO_AUXILIO_EMERGENCIAL = "Auxílio Emergencial";
	public static final String SELECAO_INICIAO_ACADEMICA = "Iniciação Acadêmica";
}
