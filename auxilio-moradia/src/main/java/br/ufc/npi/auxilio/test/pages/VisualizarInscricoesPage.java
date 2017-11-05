package br.ufc.npi.auxilio.test.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static br.ufc.npi.auxilio.utils.ConstantsTest.URL_PAGE_VISUALIZAR_INSCRICAO_52;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class VisualizarInscricoesPage {

	private WebDriver driver;
	private WebElement linhaAluno;
	
	public VisualizarInscricoesPage(WebDriver driver) {
		this.driver = driver;
	}

	public void acessar() {
		driver.get(URL_PAGE_VISUALIZAR_INSCRICAO_52);
	}
	
	public String cabecalho() {
		return driver.getTitle();
	}
	
	public boolean aluno(String aluno){		
		WebElement tabela = driver.findElement(By.xpath("//table[@id='visualizar-inscricoes']//td//a[contains(text(),'"+aluno+"')]"));
		this.linhaAluno = (tabela.findElement(By.xpath("./.."))).findElement(By.xpath("./.."));
		if(linhaAluno == null) return false;
		return true;
	}
	
	public boolean analiseDocumento(String valor, String cor){
		List<WebElement> childs = this.linhaAluno.findElements(By.cssSelector("td"));
		WebElement link = childs.get(2).findElement(By.xpath(".//*"));
		WebElement chip = link.findElement(By.xpath(".//*"));
		if(corParaClasse(cor).equals(chip.getAttribute("class"))
				&& valor.equals(chip.getText())) return true;
		return false;
	}
	
	public boolean entrevista(String valor, String cor){
		List<WebElement> childs = this.linhaAluno.findElements(By.cssSelector("td"));
		WebElement link = childs.get(3).findElement(By.xpath(".//*"));
		WebElement chip = link.findElement(By.xpath(".//*"));
		if(corParaClasse(cor).equals(chip.getAttribute("class"))
				&& valor.equals(chip.getText())) return true;
		return false;
	}
	
	public boolean visita(String valor, String cor){
		List<WebElement> childs = this.linhaAluno.findElements(By.cssSelector("td"));
		WebElement link = childs.get(4).findElement(By.xpath(".//*"));
		WebElement chip = link.findElement(By.xpath(".//*"));
		if(corParaClasse(cor).equals(chip.getAttribute("class"))
				&& valor.equals(chip.getText())) return true;
		return false;
	}
	
	public boolean parecer(String valor, String cor){
		WebElement chip = this.linhaAluno.findElement(By.id("inscResult"));
		if(corParaClasse(cor).equals(chip.getAttribute("class"))
				&& valor.equals(chip.getText())) return true;
		return false;
	}
	
	public void clickParecerFinal() {
		acessar();
		WebElement parecerFinal = driver.findElement(By.xpath(".//*[@id='visualizar-inscricoes']/thead/tr/th[6]"));
		assertEquals(parecerFinal.getText(), "Parecer Final");
	}
	
	public void clickCampoObservacoes() {
		driver.findElement(By.id("observacao")).click();
	}
	
	public void verificaCampoResultado() {
		WebElement campo = driver.findElement(By.xpath(".//*[@id='modal1']/div/form/div[2]/label"));
		assertNotNull(campo);
	}
	
	public void verificaStatusDeferido(String valor) {
		WebElement documentacao = driver.findElement(By.xpath(".//a[@href='/documentacao/inscricao/27']/div[@id='inscDocumentacao']"));
		WebElement entrevista = driver.findElement(By.xpath(".//a[@href='/entrevista/27']/div[@id='inscEntrevista']"));
		WebElement visita = driver.findElement(By.xpath(".//a[@href='/visita/27']/div[@id='inscVisita']"));
		
		assertEquals(documentacao.getText(), valor);
		assertEquals(entrevista.getText(), valor);
		assertEquals(visita.getText(), valor);
	}
	
	public void verificaStatus(String documentacao, String valor) {
		WebElement iddoc;
		switch(documentacao){
			case "AnaliseDocumentacao":
				iddoc= driver.findElement(By.xpath("//a[@href='/documentacao/inscricao/28']/div[@id='inscDocumentacao']"));
				assertEquals(iddoc.getText(), valor);
				break;
			case "Entrevista":
				iddoc= driver.findElement(By.xpath("//a[@href='/entrevista/28']/div[@id='inscEntrevista']"));
				assertEquals(iddoc.getText(), valor);
				break;
			case "VisitaDomiciliar":
				iddoc= driver.findElement(By.xpath("//a[@href='/visita/28']/div[@id='inscVisita']"));
				assertEquals(iddoc.getText(), valor);
				break;
		}
	}
	
	public void verificaStatusDeferidoParecerFinal() {
		WebElement botaoParecerFinal = driver.findElement(By.xpath(".//a[@data-id='27']/div[@id='inscResult']"));
		assertEquals(botaoParecerFinal.getText(), "Deferido");
	}
	
	public void verificaStatusIndeferidoParecerFinal() {
		WebElement botaoParecerFinal = driver.findElement(By.xpath(".//a[@data-id='28']/div[@id='inscResult']"));
		assertEquals(botaoParecerFinal.getText(), "Indeferido");
	}
	
	public void verificaStatusDeferidoAnaliseDocumentacao() {
		WebElement botaoDocumentacao = driver.findElement(By.xpath(".//a[@href='/documentacao/inscricao/27']/div[@id='inscDocumentacao']"));
		assertEquals(botaoDocumentacao.getText(), "Deferido");
	}
	
	public void verificaStatusIndeferidoAnaliseDocumentacao() {
		WebElement botaoDocumentacao = driver.findElement(By.xpath(".//a[@href='/documentacao/inscricao/26']/div[@id='inscDocumentacao']"));
		assertEquals(botaoDocumentacao.getText(), "Indeferido");
	}
	
	public void verificaStatusDeferidoEntrevista() {
		WebElement botaoEntrevista = driver.findElement(By.xpath(".//a[@href='/entrevista/27']/div[@id='inscEntrevista']"));
		assertEquals(botaoEntrevista.getText(), "Deferido");
	}
	
	public void verificaStatusIndeferidoEntrevista() {
		WebElement botaoEntrevista = driver.findElement(By.xpath(".//a[@href='/entrevista/28']/div[@id='inscEntrevista']"));
		assertEquals(botaoEntrevista.getText(), "Indeferido");
	}
	
	public void verificaStatusDeferidoVisitaDomiciliar() {
		WebElement botaoVisita = driver.findElement(By.xpath(".//a[@href='/visita/27']/div[@id='inscVisita']"));
		assertEquals(botaoVisita.getText(), "Deferido");
	}
	
	public void verificaStatusIndeferidoVisitaDomiciliar() {
		WebElement botaoVisita = driver.findElement(By.xpath(".//a[@href='/visita/28']/div[@id='inscVisita']"));
		assertEquals(botaoVisita.getText(), "Indeferido");
	}
	
	public void clickButtonParecerFinal() {
		WebElement botaoParecer = driver.findElement(By.xpath(".//div[@id='inscResult']"));
		botaoParecer.click();
	}
	
	public void alterarDadosCampoObservacoes(String texto) {
		driver.findElement(By.id("observacao")).sendKeys(texto);
		WebElement botaoSalvar = driver.findElement(By.xpath(".//*[@id='modal1']/div/form/div[4]/button"));
		botaoSalvar.click();
	}
 	
	
	public void alterarCampoResultado(String alteracao) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;

		switch (alteracao) {
		case "DEFERIDO":
			driver.findElement(By.xpath(".//div[@id='inscResult']")).click();
			driver.findElement(By.xpath(".//*[@id='modal1']/div/form/div[2]/div/input")).click();
			executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(".//*/li[1]/span")));
			break;
		case "INDEFERIDO":
			driver.findElement(By.xpath(".//div[@id='inscResult']")).click();
			driver.findElement(By.xpath(".//*[@id='modal1']/div/form/div[2]/div/input")).click();
			executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*/li[2]/span")));
			break;
		case "NAO_AVALIADO":
			driver.findElement(By.xpath(".//div[@id='inscResult']")).click();
			driver.findElement(By.xpath(".//*[@id='modal1']/div/form/div[2]/div/input")).click();
			executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*/li[3]/span")));
			break;
		}
		WebElement botaoSalvar = driver.findElement(By.xpath(".//*[@id='modal1']/div/form/div[4]/button"));
		botaoSalvar.click();
	}
	
	public void verificaFormularioExibido() {
		WebElement modal = driver.findElement(By.xpath(".//div[@id='modal1']"));
		assertEquals(modal.getAttribute("class"), "modal open velocity-animating");
	}
	
	public void verificaResultadoParecerFinal(String valorParecer) {
		WebElement idParecer;

		switch (valorParecer) {
		case "Deferido":
			idParecer = driver.findElement(By.xpath(".//a[@data-id='27']/div[@id='inscResult']"));
			assertEquals(idParecer.getText(), valorParecer);
			break;
		case "Indeferido":
			idParecer = driver.findElement(By.xpath(".//a[@data-id='28']/div[@id='inscResult']"));
			assertEquals(idParecer.getText(), valorParecer);
			break;
		}
	}
	
	public void verificaCorIconeVerde() {
		WebElement botao = driver.findElement(By.xpath(".//div[@class='chip green lighten-1']"));
		WebElement cor = driver.findElement(By.cssSelector(".green.lighten-1"));
		assertEquals(botao, cor);
	}
	
	public void verificaCorIconeVermelho() {
		WebElement botao = driver.findElement(By.xpath(".//div[@class='chip red lighten-1']"));
		WebElement cor = driver.findElement(By.cssSelector(".red.lighten-1"));
		assertEquals(botao, cor);
	}
	
	public void verificaFormularioNaoExibido() {
		WebElement modal = driver.findElement(By.xpath(".//div[@id='modal1']"));
		assertEquals(modal.getAttribute("class"), "modal");
	}
	
	public void verificarAlteracaoCampoObservacao(String texto) {
		WebElement observacao = driver.findElement(By.xpath(".//*[@id='observacao']"));
		assertEquals(observacao.getAttribute("value"), texto);
	}
	
	public void verificaPaginaVisualizarInscricoes() {
		String title = driver.getTitle();
		assertEquals(title, "Auxílio - Visualizar Inscrições");
	}
	
	public void alteracaoCampoResultado(String alteracao) {
		WebElement classe;

		switch (alteracao) {
		case "DEFERIDO":
			driver.findElement(By.xpath(".//div[@id='inscResult']")).click();
			driver.findElement(By.xpath("//*[@id='modal1']/div/form/div[2]/div/input")).click();
			classe = driver.findElement(
					By.xpath("//div[@id='modal1']/div/form/div[@class='col s12 input-field']/div/ul/li[1]"));
			driver.findElement(By.xpath("//*[@id='modal1']/div/form/div[4]/a")).click();
			assertEquals(classe.getAttribute("class"), "active");
			break;
		case "INDEFERIDO":
			driver.findElement(By.xpath(".//div[@id='inscResult']")).click();
			driver.findElement(By.xpath("//*[@id='modal1']/div/form/div[2]/div/input")).click();
			classe = driver.findElement(
					By.xpath("//div[@id='modal1']/div/form/div[@class='col s12 input-field']/div/ul/li[2]"));
			driver.findElement(By.xpath("//*[@id='modal1']/div/form/div[4]/a")).click();
			assertEquals(classe.getAttribute("class"), "active");
			break;
		case "NAO_AVALIADO":
			driver.findElement(By.xpath(".//div[@id='inscResult']")).click();
			driver.findElement(By.xpath("//*[@id='modal1']/div/form/div[2]/div/input")).click();
			classe = driver.findElement(
					By.xpath("//div[@id='modal1']/div/form/div[@class='col s12 input-field']/div/ul/li[3]"));
			driver.findElement(By.xpath("//*[@id='modal1']/div/form/div[4]/a")).click();
			assertEquals(classe.getAttribute("class"), "active");
			break;
		}
	}
	
	public void verificaEditavel() {
		WebElement result = driver.findElement(By.xpath(".//*[@id='modal1']/div/form/div[2]/div/input"));
		WebElement observacao = driver.findElement(By.xpath(".//*[@id='observacao']"));

		assertEquals(result.getAttribute("value"), "DEFERIDO");
		assertEquals(observacao.getTagName(), "textarea");
	}
	
	private String corParaClasse(String cor){
		switch (cor) {
			case "Cinza":
				return "chip grey lighten-1";
			case "Verde":
				return "chip green lighten-1";
			case "Vermelho":
				return "chip red lighten-1";
		}
		
		return null;
	}
}
