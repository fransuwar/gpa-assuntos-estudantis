package br.ufc.npi.auxilio.test.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VisualizarSelecaoPage {

	private WebDriver driver;
	private WebElement linhaAluno;
	
	public VisualizarSelecaoPage(WebDriver driver) {
		this.driver = driver;
	}

	public void acessar(String site) {
		driver.get(site);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
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
	public boolean analiseDocumento(String estado, String cor){
		List<WebElement> childs = this.linhaAluno.findElements(By.cssSelector("td"));
		WebElement link = childs.get(2).findElement(By.xpath(".//*"));
		WebElement chip = link.findElement(By.xpath(".//*"));
		if(corParaClasse(cor).equals(chip.getAttribute("class"))
				&& estado.equals(chip.getText())) return true;
		return false;
	}
	
	public boolean entrevista(String estado, String cor){
		List<WebElement> childs = this.linhaAluno.findElements(By.cssSelector("td"));
		WebElement link = childs.get(3).findElement(By.xpath(".//*"));
		WebElement chip = link.findElement(By.xpath(".//*"));
		if(corParaClasse(cor).equals(chip.getAttribute("class"))
				&& estado.equals(chip.getText())) return true;
		return false;
	}
	
	public boolean visita(String estado, String cor){
		List<WebElement> childs = this.linhaAluno.findElements(By.cssSelector("td"));
		WebElement link = childs.get(4).findElement(By.xpath(".//*"));
		WebElement chip = link.findElement(By.xpath(".//*"));
		if(corParaClasse(cor).equals(chip.getAttribute("class"))
				&& estado.equals(chip.getText())) return true;
		return false;
	}
	
	public boolean parecer(String estado, String cor){
		WebElement chip = this.linhaAluno.findElement(By.id("inscResult"));
		if(corParaClasse(cor).equals(chip.getAttribute("class"))
				&& estado.equals(chip.getText())) return true;
		return false;
	}
	
	private String corParaClasse(String cor){
		switch (cor.toUpperCase()) {
			case "CINZA":
				return "chip grey lighten-1";
			case "VERDE":
				return "chip green lighten-1";
			case "VERMELHO":
				return "chip red lighten-1";
			default:
				return "chip teal lighten-1";
		}
	}
	
	
}
