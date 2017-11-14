package br.ufc.npi.auxilio.test.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.ufc.npi.auxilio.utils.ConstantsTest;

public class VisualizarSelecaoPage {

	private WebDriver driver;

	/*
	private By fieldSelecao = By.id(ConstantsTest.ID_FIELD_TIPO);
	private By fieldAno = By.id(ConstantsTest.ID_FIELD_ANO);
	private By fieldInicio = By.id(ConstantsTest.ID_FIELD_INICIO);
	private By fieldTermino = By.id(ConstantsTest.ID_FIELD_TERMINO);
	private By fieldVagas = By.id(ConstantsTest.ID_FIELD_VAGAS);
	private By buttonSalvar = By.id(ConstantsTest.ID_BUTTON_SALVAR);
	private By buttonCancelar = By.id(ConstantsTest.ID_BUTTON_CANCELAR);
	*/
	
	private By buttonExibirResultado = By.id(ConstantsTest.ID_BUTTON_EXIBIR_RESULTADO);
	private By buttonAlunoDeferido = By.id(ConstantsTest.ID_BUTTON_ALUNO_DEFERIDO);
	private By buttonAlunoIndeferido = By.id(ConstantsTest.ID_BUTTON_ALUNO_INDEFERIDO);
	private By buttonAlunoReserva = By.id(ConstantsTest.ID_BUTTON_ALUNO_RESERVA);
	private By buttonMenuResultado = By.id(ConstantsTest.ID_BUTTON_MENU);
	private By buttonMenuResultadoFinalDeferidos = By.xpath("//*[@id='alunos-deferidos']/div/div[1]/div/a");
	private By buttonMenuResultadoFinalIndeferidos = By.xpath("//*[@id='alunos-indeferidos']/div/div[1]/div/a");
	private By buttonMenuResultadoFinalReserva = By.xpath("//*[@id='alunos-reservas']/div/div[1]/div/a");
	private By buttonMenuResultadoFinal = By.id(ConstantsTest.ID_BUTTON_MENU_FINAL);
	

	public VisualizarSelecaoPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void acessar() throws InterruptedException {
		driver.get(ConstantsTest.URL_PAGE_VISUALIZAR_SELECAO_34);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	public void clicarButtonMenu() {
		driver.findElement(buttonMenuResultado).click();
	}
	
	public void clicarButtonExibirResultado() {
		driver.findElement(buttonExibirResultado).click();
	}
	
	public void clicarButtonMenuFinalDeferido() {
		driver.findElement(buttonMenuResultadoFinalDeferidos).click();
		driver.findElement(By.xpath("//ul[@id='float_relatorio_deferidos']/li/a[@data-tooltip='Exportar para pdf']")).click();
	}
	
	public void clicarButtonMenuFinalIndeferido() {
		driver.findElement(buttonMenuResultadoFinalIndeferidos).click();
		driver.findElement(By.xpath("//ul[@id='float_relatorio_indeferidos']/li/a[@data-tooltip='Exportar para pdf']")).click();
	}
	
	public void clicarButtonMenuFinalReserva() {
		driver.findElement(buttonMenuResultadoFinalReserva).click();
		driver.findElement(By.xpath("//ul[@id='float_relatorio_reservas']/li/a[@data-tooltip='Exportar para pdf']")).click();
	}
	
	public void clicarButtonDeferido(){
		driver.findElement(buttonAlunoDeferido).click();
	}
	
	
	public void clicarButtonIndeferido(){
		driver.findElement(buttonAlunoIndeferido).click();
	}
	
	public void clicarButtonReserva(){
		driver.findElement(buttonAlunoReserva).click();
	}
	
	public void downloadDocumentoDeferidos() {
        //driver.findElement(By.cssSelector("//ul[@id='float_relatorio_deferidos']/li/a[@data-tooltip='Exportar para pdf']")).click();
		driver.findElement(By.cssSelector("#float_relatorio_deferidos > li:nth-child(1) > a:nth-child(1)")).click();
        try {
        	
            Thread.sleep(500);

            Robot robo =  new Robot();
            robo.delay(100);
            robo.keyPress(KeyEvent.VK_DOWN);            
            robo.keyRelease(KeyEvent.VK_DOWN);
            
            robo.delay(1000);
            robo.keyPress(KeyEvent.VK_ENTER);
            robo.keyRelease(KeyEvent.VK_ENTER);
        
            
            
        } catch (AWTException | InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        //driver.manage().timeouts().pageLoadTimeout(2000, TimeUnit.SECONDS);

    }
    
	public void downloadDocumentoIndeferidos() {
		driver.findElement(By.cssSelector("#float_relatorio_indeferidos > li:nth-child(1) > a:nth-child(1)")).click();
        try {
        	
            Thread.sleep(500);

            Robot robo =  new Robot();
            robo.delay(100);
            robo.keyPress(KeyEvent.VK_DOWN);            
            robo.keyRelease(KeyEvent.VK_DOWN);
            
            robo.delay(1000);
            robo.keyPress(KeyEvent.VK_ENTER);
            robo.keyRelease(KeyEvent.VK_ENTER);
            
        } catch (AWTException | InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
	
	public void downloadDocumentoReservas() {
		driver.findElement(By.cssSelector("#float_relatorio_reservas > li:nth-child(1) > a:nth-child(1)")).click();
        try {
        	
            Thread.sleep(500);

            Robot robo =  new Robot();
            robo.delay(100);
            robo.keyPress(KeyEvent.VK_DOWN);            
            robo.keyRelease(KeyEvent.VK_DOWN);
            
            robo.delay(1000);
            robo.keyPress(KeyEvent.VK_ENTER);
            robo.keyRelease(KeyEvent.VK_ENTER);
            
        } catch (AWTException | InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
	
    public boolean arquivoBaixado(String path) {
        String caminho = "/home/joao.monteiro/Downloads"+path;
        File file =  new File(caminho);
        if(file.exists()) return true;
        return false;
    }
	
}
