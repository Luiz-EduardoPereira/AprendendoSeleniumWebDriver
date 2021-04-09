import static br.ce.luiz.core.DriverFactory.*;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import br.ce.luiz.core.DSL;

public class TesteGoogle {
	private DSL dsl;
	@Before
	public void inicializar() {
		getDriver();
		dsl = new DSL();
	}
	@After
	public void fecharBrowser() {
		killDriver();
	}
	public void pausa() {
		getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	@Test
	public void abrirGuiaGoogle() {
		pausa();
		getDriver().get("https://www.google.com.br/");
	}
	@Test
	public void realizarPesquisaGoogle(){
		abrirGuiaGoogle();
		pausa();
		dsl.escrever(By.xpath("//input[@name='q']"), "Java");
		getDriver().findElement(By.xpath("//div[@class='FPdoLc tfB0Bf']//input[@name='btnK']")).sendKeys(Keys.ENTER);
		Assert.assertEquals("Java", dsl.obterTexto(By.xpath("//span[.='Java']")));
	}
	@Test
	public void validarTituloGoogle() {
		abrirGuiaGoogle();
		pausa();
		Assert.assertEquals("Google", getDriver().getTitle());
	}
	@Test
	public void validarUrlGoogle() {
		abrirGuiaGoogle();
		pausa();
		Assert.assertEquals("https://www.google.com.br/", getDriver().getCurrentUrl());
	}
}
