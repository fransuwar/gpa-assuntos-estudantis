package br.ufc.npi.auxilio.test.pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.gargoylesoftware.htmlunit.javascript.host.Console;

import br.ufc.npi.auxilio.utils.ConstantsTest;
import ch.qos.logback.core.net.SyslogOutputStream;

public class VisualizarInscricoesPage {

	private WebDriver driver;
	
	public VisualizarInscricoesPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void acessar(String selecao) {
		driver.get("http://127.0.0.1:8080/selecao/inscricoes/" + selecao);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	}
	
	public void verificarDeferido(String posicao) {
		WebElement parecer = driver.findElement(By.xpath("//*[@id='visualizar-inscricoes']/tbody/tr[" + posicao + "]/td[6]/a/div"));

		assertEquals(parecer.getAttribute("class"), "chip green lighten-1");
	}
	
	public void verificarIndeferido(String posicao) {
		WebElement parecer = driver.findElement(By.xpath("//*[@id='visualizar-inscricoes']/tbody/tr[" + posicao + "]/td[6]/a/div"));
		
		assertEquals(parecer.getAttribute("class"), "chip red lighten-1");
	}
	
	public void verificarMovel(String posicao) {
		WebElement tr = driver.findElement(By.xpath("//*[@id='visualizar-inscricoes']/tbody/tr[" + posicao +"]"));
		assertEquals(tr.getAttribute("class"), "card movel ui-sortable-handle");
	}
	
	public void verificarImovel(String posicao) {
		WebElement tr = driver.findElement(By.xpath("//*[@id='visualizar-inscricoes']/tbody/tr[" + posicao + "]"));
		assertEquals(tr.getAttribute("class"), "card imovel");
	}
	
	public void alterarSelecionado(String posicao) {
		driver.findElement(By.xpath("//*[@id='visualizar-inscricoes']/tbody/tr[" + posicao + "]/td[7]/div/div/label/span")).click();
	}
	
	public void verificaToggleSelecionados(String posicao) {
		WebElement input = driver.findElement(By.xpath("//*[@id='visualizar-inscricoes']/tbody/tr[" + posicao + "]/td[7]/div/div/label/input"));
		
		if(input.getAttribute("checked") == null) {
			assertEquals(input.getAttribute("checked"), null);
		} else {
			assertEquals(input.getAttribute("checked"), "true");
		}
	}
	
	public void verificaToggleDesativado(String posicao) {
		WebElement input = driver.findElement(By.xpath("//*[@id='visualizar-inscricoes']/tbody/tr[" + posicao + "]/td[7]/div/div/label/input"));
		assertEquals(input.getAttribute("disabled"), "true");
	}
	
	public void alterarPosicaoAluno(String posicao1, String posicao2) {
		WebElement From = driver.findElement(By.xpath("//*[@id='visualizar-inscricoes']/tbody/tr[" + posicao1 + "]"));
		WebElement To = driver.findElement(By.xpath("//*[@id='visualizar-inscricoes']/tbody/tr[" + posicao2 + "]"));
		
		Actions builder = new Actions(driver);
		Action dragAndDrop = builder.clickAndHold(From)
							.moveToElement(To)
							.release(To)
							.build();
		
		dragAndDrop.perform();
	}
	
	public void verificarMudancaPosicao(String posicaoAtual) {
		WebElement posicao = driver.findElement(By.xpath("//*[@id='visualizar-inscricoes']/tbody/tr[" + posicaoAtual + "]/td[1]"));
		assertEquals(posicao.getText(), posicaoAtual);
	}

}
