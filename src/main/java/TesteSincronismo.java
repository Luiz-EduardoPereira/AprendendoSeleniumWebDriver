import static br.ce.luiz.core.DriverFactory.getDriver;
import static br.ce.luiz.core.DriverFactory.killDriver;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ce.luiz.core.DSL;

public class TesteSincronismo {
	
	private DSL dsl;
	
	@Before
	public void inicializar() {
		getDriver().get("file:///" + System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	@After
	public void fecharBrowser() {
		killDriver();
	}
	@Test
	public void btnRespostaDemoradaEsperaFixa() throws InterruptedException {
		dsl.clicar(By.id("buttonDelay"));
		Thread.sleep(5000);
		dsl.escrever("novoCampo", "Resposta demorada !");
		Assert.assertEquals("Resposta demorada !", dsl.obterValorCampo("novoCampo"));
	}
	@Test
	public void btnRespostaDemoradaEsperaImplicita() throws InterruptedException {
		dsl.clicar(By.id("buttonDelay"));
		getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		dsl.escrever("novoCampo", "Resposta demorada !");
		Assert.assertEquals("Resposta demorada !", dsl.obterValorCampo("novoCampo"));
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
	@Test
	public void btnRespostaDemoradaEsperaExplicita() throws InterruptedException {
		dsl.clicar(By.id("buttonDelay"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.escrever("novoCampo", "Resposta demorada !");
		Assert.assertEquals("Resposta demorada !", dsl.obterValorCampo("novoCampo"));
	}
}
