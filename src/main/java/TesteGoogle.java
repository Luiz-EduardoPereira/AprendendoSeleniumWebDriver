import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteGoogle {
	private static WebDriver driver;
	private DSL dsl;
	@Before
	public void inicializar() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		dsl = new DSL(driver);
	}
	@After
	public void fecharBrowser() {
		driver.quit();
	}
	public void pausa() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	@Test
	public void abrirGuiaGoogle() {
		pausa();
		driver.get("https://www.google.com.br/");
	}
	@Test
	public void realizarPesquisaGoogle(){
		abrirGuiaGoogle();
		pausa();
		dsl.escreve(By.xpath("//input[@name='q']"), "Java");
		driver.findElement(By.xpath("//div[@class='FPdoLc tfB0Bf']//input[@name='btnK']")).sendKeys(Keys.ENTER);
		Assert.assertEquals("Java", dsl.obterTexto(By.xpath("//span[.='Java']")));
	}
	@Test
	public void validarTituloGoogle() {
		abrirGuiaGoogle();
		pausa();
		Assert.assertEquals("Google", driver.getTitle());
	}
	@Test
	public void validarUrlGoogle() {
		abrirGuiaGoogle();
		pausa();
		Assert.assertEquals("https://www.google.com.br/", driver.getCurrentUrl());
	}
}
