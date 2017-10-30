package br.ufc.npi.auxilio.test.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.ufc.npi.auxilio.utils.ConstantsTest;

public class SelecaoPage {

	private WebDriver driver;
	
	public SelecaoPage(WebDriver driver) {
		this.driver = driver;
	}

	public void escolherSelecao(int value) {
		driver.findElement(By.cssSelector("a[href*='/auxilio/selecao/inscricoes/"+value+"']")).click();
	}
	
}
