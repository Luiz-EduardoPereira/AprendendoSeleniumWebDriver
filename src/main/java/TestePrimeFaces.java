import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestePrimeFaces {
	private WebDriver driver;
	private DSL dsl;
	@Before
	public void inicializar() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		dsl = new DSL(driver);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	@After
	public void fecharBrowser() {
		driver.quit();
	}
	@Test
	public void usarRadioPrimeFaces() {
		driver.get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml?jfwid=2879c");
		dsl.clicar(By.xpath("//input[@id='j_idt300:console:0']/../..//span"));
		Assert.assertTrue(dsl.checarMarcacao("j_idt300:console:0"));
		dsl.clicar(By.xpath("//label[.='Option2']/..//span"));
		Assert.assertTrue(dsl.checarMarcacao("j_idt300:console:1"));
	}
	@Test
	public void usarComboPrimeFaces() {
		driver.get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml?jfwid=4fa5f");
		dsl.selecionarComboPrimeFaces("j_idt299:option", "Option2");
		Assert.assertEquals("Option2", dsl.obterTexto("j_idt299:option_label"));
	}
	@Test
	public void usarFormLayoutPrimeFaces() {
		driver.get("https://www.primefaces.org/showcase/primeflex/formlayout.xhtml?jfwid=642cb");
		dsl.escrever("firstname1", "Daceos");
		dsl.escrever("lastname1", "Clicen");
		Assert.assertEquals("Dacoes", dsl.obterTexto("firstname1"));
		Assert.assertEquals("Clicen", dsl.obterTexto("lastname1"));
	}
	@Test
	public void usarCheckBoxPrimeFaces() {
		driver.get("https://www.primefaces.org/showcase/primeflex/formlayout.xhtml?jfwid=642cb");
		dsl.clicar(By.xpath("//*[@for='city5']/..//span"));
		dsl.clicar(By.xpath("//*[@for='city6']/..//span"));
		dsl.checarMarcacao(By.xpath("//*[@for='city5']/..//span"));
		dsl.checarMarcacao(By.xpath("//*[@for='city5']/..//span"));
	}
	@Test
	public void usarValidacaoFormPrimeFaces() {
		driver.get("https://www.primefaces.org/showcase/ui/ajax/validation.xhtml?jfwid=642cb");
		dsl.clicar(By.id("j_idt299:j_idt304"));
		Assert.assertEquals("Firstname: Validation Error: Value is required.", dsl.obterTexto(By.xpath("//*[@id='j_idt299:msgs']//li/span[.='Firstname: Validation Error: Value is required.']")));
		Assert.assertEquals("Lastname: Validation Error: Value is required.", dsl.obterTexto(By.xpath("//*[@id='j_idt299:msgs']//li/span[.='Lastname: Validation Error: Value is required.']")));
		dsl.escrever("j_idt299:firstname", "Dacoes");
		dsl.escrever("j_idt299:lastname", "Clicen");
		dsl.clicar(By.id("j_idt299:j_idt304"));
	}
	/*public void usarTelaPaginada() {
		driver.get("https://www.primefaces.org/showcase/ui/data/dataexporter/basic.xhtml?jfwid=642cb");
		
	}*/
}
