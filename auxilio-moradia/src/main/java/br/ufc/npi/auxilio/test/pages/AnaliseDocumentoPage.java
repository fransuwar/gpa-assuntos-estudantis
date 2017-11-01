package br.ufc.npi.auxilio.test.pages;

import static org.mockito.Matchers.eq;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class AnaliseDocumentoPage {

	private WebDriver driver;
	
	
	public AnaliseDocumentoPage(WebDriver driver) {
		this.driver = driver;
	}
	public void clicarAnaliseDocumento(Integer value) {
		driver.findElement(By.cssSelector("a[href*='/documentacao/inscricao/"+value+"']")).click();
	}
	
	public String getTituloDaPagina() {
		return driver.getTitle();
	}
	
	public boolean documentosVisiveis(String doc1,String doc2) {
		String pageDoc1 = driver.findElement(By.cssSelector("div.card:nth-child(2) > div:nth-child(1) > "
				+ "table:nth-child(2) > tbody:nth-child(2) > "
				+ "tr:nth-child(1) > td:nth-child(1)")).getText();
		String pageDoc2 = driver.findElement(By.cssSelector("div.card:nth-child(2) > div:nth-child(1) >"
				+ " table:nth-child(2) > tbody:nth-child(2) > "
				+ "	tr:nth-child(2) > td:nth-child(1)")).getText();
		if(doc1.equals(pageDoc1) && doc2.equals(pageDoc2)) return true;
		return false;
	}
	
	public void downloadDocumento(Integer value) {
		driver.findElement(By.cssSelector("a[href*='/documentacao/documento/29/download/"
	+value+"']")).click();
		try {
			Thread.sleep(3000);

			Robot robo =  new Robot();
			robo.delay(100);
			robo.keyPress(KeyEvent.VK_DOWN);			
			robo.keyRelease(KeyEvent.VK_DOWN);
			
			robo.delay(100);
			robo.keyPress(KeyEvent.VK_ENTER);
			robo.keyRelease(KeyEvent.VK_ENTER);
		
			
			
		} catch (AWTException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//driver.manage().timeouts().pageLoadTimeout(2000, TimeUnit.SECONDS);

	}
	
	public boolean arquivoBaixado(String path) {
		String caminho = "/home/afonso.neto/Downloads/"+path;
		File file =  new File(caminho);
		if(file.exists()) return true;
		return false;
	}
}


