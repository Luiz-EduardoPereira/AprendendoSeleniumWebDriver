import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteGoogle {
	WebDriver driver;
	public void pausa() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	public void inicializar() {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	@Test
	public void abrirGuiaGoogle() {
		inicializar();
		pausa();
		driver.get("https://www.google.com.br/");
	}
	@Test
	public void realizarPesquisaGoogle(){
		inicializar();
		abrirGuiaGoogle();
		pausa();
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Java");
		driver.findElement(By.xpath("//div[@class='FPdoLc tfB0Bf']//input[@name='btnK']")).click();
	}
	@Test
	public void validarTituloGoogle() {
		inicializar();
		abrirGuiaGoogle();
		pausa();
		Assert.assertEquals("Google", driver.getTitle());
	}
	@Test
	public void validarUrlGoogle() {
		inicializar();
		abrirGuiaGoogle();
		pausa();
		Assert.assertEquals("https://www.google.com.br/", driver.getCurrentUrl());
	}
}
