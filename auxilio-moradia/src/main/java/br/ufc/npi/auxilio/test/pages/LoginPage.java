package br.ufc.npi.auxilio.test.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.ufc.npi.auxilio.utils.ConstantsTest;

public class LoginPage {
	
	private WebDriver driver;
	
	private By fieldUsername = By.id(ConstantsTest.ID_FIELD_USERNAME);
	private By fieldPassword = By.id(ConstantsTest.ID_FIELD_PASSWORD);
	private By buttonEntrar = By.id(ConstantsTest.ID_BUTTON_ENTRAR);
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void acessar() {
		driver.get(ConstantsTest.URL_PAGE_LOGIN);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}
	
	public void acessar(String site){
		driver.get(site);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}
	
	public void logar() {
		driver.findElement(fieldUsername).clear();
		driver.findElement(fieldUsername).sendKeys(ConstantsTest.LOGIN_VALUE);
		driver.findElement(fieldPassword).clear();
		driver.findElement(fieldPassword).sendKeys(ConstantsTest.PASSWORD_VALUE);
		driver.findElement(buttonEntrar).click();
	}
	
	
}
