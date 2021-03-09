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
		driver.get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml?jfwid=2879c");
		dsl = new DSL(driver);
	}
	@After
	public void fecharBrowser() {
		driver.quit();
	}
	@Test
	public void usarRadioPrimeFaces() {
		dsl.clicar(By.xpath("//input[@id='j_idt300:console:0']/../..//span"));
		Assert.assertTrue(dsl.checarMarcacao("j_idt300:console:0"));
		dsl.clicar(By.xpath("//label[.='Option2']/..//span"));
		Assert.assertTrue(dsl.checarMarcacao("j_idt300:console:1"));

	}
}
