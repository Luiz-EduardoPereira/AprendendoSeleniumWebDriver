import static br.ce.luiz.core.DriverFactory.getDriver;
import static br.ce.luiz.core.DriverFactory.killDriver;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.ce.luiz.core.DSL;

public class TestePrimeFaces {
	private DSL dsl;
	@Before
	public void inicializar() {
		getDriver();
		dsl = new DSL();
		getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	@After
	public void fecharBrowser() {
		killDriver();
	}
	@Test
	public void usarRadioPrimeFaces() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml?jfwid=2879c");
		dsl.clicar(By.xpath("//input[@id='j_idt300:console:0']/../..//span"));
		Assert.assertTrue(dsl.checarMarcacao("j_idt300:console:0"));
		dsl.clicar(By.xpath("//label[.='Option2']/..//span"));
		Assert.assertTrue(dsl.checarMarcacao("j_idt300:console:1"));
	}
	@Test
	public void usarComboPrimeFaces() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml?jfwid=4fa5f");
		dsl.selecionarComboPrimeFaces("j_idt299:option", "Option2");
		Assert.assertEquals("Option2", dsl.obterTexto("j_idt299:option_label"));
	}
	@Test
	public void usarFormLayoutPrimeFaces() {
		getDriver().get("https://www.primefaces.org/showcase/primeflex/formlayout.xhtml?jfwid=642cb");
		dsl.escrever("firstname1", "Daceos");
		dsl.escrever("lastname1", "Clicen");
		Assert.assertEquals("Dacoes", dsl.obterTexto("firstname1"));
		Assert.assertEquals("Clicen", dsl.obterTexto("lastname1"));
	}
	@Test
	public void usarCheckBoxPrimeFaces() {
		getDriver().get("https://www.primefaces.org/showcase/primeflex/formlayout.xhtml?jfwid=642cb");
		dsl.clicar(By.xpath("//*[@for='city5']/..//span"));
		dsl.clicar(By.xpath("//*[@for='city6']/..//span"));
		dsl.checarMarcacao(By.xpath("//*[@for='city5']/..//span"));
		dsl.checarMarcacao(By.xpath("//*[@for='city5']/..//span"));
	}
	@Test
	public void usarValidacaoFormPrimeFaces() {
		getDriver().get("https://www.primefaces.org/showcase/ui/ajax/validation.xhtml?jfwid=642cb");
		dsl.clicar(By.id("j_idt299:j_idt304"));
		Assert.assertEquals("Firstname: Validation Error: Value is required.", dsl.obterTexto(By.xpath("//*[@id='j_idt299:msgs']//li/span[.='Firstname: Validation Error: Value is required.']")));
		Assert.assertEquals("Lastname: Validation Error: Value is required.", dsl.obterTexto(By.xpath("//*[@id='j_idt299:msgs']//li/span[.='Lastname: Validation Error: Value is required.']")));
		dsl.escrever("j_idt299:firstname", "Dacoes");
		dsl.escrever("j_idt299:lastname", "Clicen");
		dsl.clicar(By.id("j_idt299:j_idt304"));
	}
	/*public void usarTelaPaginada() {
		getDriver().get("https://www.primefaces.org/showcase/ui/data/dataexporter/basic.xhtml?jfwid=642cb");
		
	}*/
}
