import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteGoogle {
	WebDriver driver = new ChromeDriver();
	public void inicializar() {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
		driver.manage().window().maximize();
	}
	@Test
	public void abrirGoogleChrome() {
		inicializar();
		driver.get("https://www.google.com.br");
	}
	@Test
	public void realizarPesquisa(){
		
	}
	@Test
	public void validarTituloGoogle() {
		inicializar();
		abrirGoogleChrome();
		Assert.assertEquals("Google", driver.getTitle());
	}
	@Test
	public void validarUrlGoogle() {
		inicializar();
		abrirGoogleChrome();
		Assert.assertEquals("https://www.google.com.br/", driver.getCurrentUrl());
	}
}
