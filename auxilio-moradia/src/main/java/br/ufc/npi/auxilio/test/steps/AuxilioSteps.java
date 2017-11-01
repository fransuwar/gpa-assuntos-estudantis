package br.ufc.npi.auxilio.test.steps;

import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.BeforeStory;
import org.openqa.selenium.WebDriver;

import br.ufc.npi.auxilio.test.pages.PageFactory;

public class AuxilioSteps {
	
	private final WebDriver driver;
	private final PageFactory pages;
	
	public AuxilioSteps(WebDriver driver) {
		this.driver = driver;
		this.pages = new PageFactory(driver);
	}
	
	@BeforeStory
	public void efetuarLogin() {
		pages.login().acessar();
		pages.login().logar();
	}
	
	@AfterStories
	public void close() {
		this.driver.close();
	}
	
}
