import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ce.luiz.core.DSL;

public class TesteAjax {
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializar() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml?jfwid=71d53");
		dsl = new DSL(driver);
	}
	@After
	public void fecharBrowser() {
		driver.quit();
	}
	@Test
	public void testarAjax() { 
		dsl.escrever("j_idt302:name", "Teste");
		dsl.clicar(By.id("j_idt302:j_idt306"));
		WebDriverWait espera = new WebDriverWait(driver, 30);
		espera.until(ExpectedConditions.textToBe(By.id("j_idt302:display"), "Teste"));
		Assert.assertEquals("Teste", dsl.obterTexto("j_idt302:display"));
	}
}
