package br.ce.luiz.test;
import static br.ce.luiz.core.DriverFactory.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import br.ce.luiz.core.DSL;

public class TesteAjax {
	private DSL dsl;
	
	@Before
	public void inicializar() {
		getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml?jfwid=71d53");
		dsl = new DSL();
	}
	@After
	public void fecharBrowser() {
		killDriver();
	}
	@Test
	public void testarAjax() { 
		dsl.escrever("j_idt302:name", "Teste");
		dsl.clicar(By.id("j_idt302:j_idt306"));
		WebDriverWait espera = new WebDriverWait(getDriver(), 30);
		espera.until(ExpectedConditions.textToBe(By.id("j_idt302:display"), "Teste"));
		Assert.assertEquals("Teste", dsl.obterTexto("j_idt302:display"));
	}
}
