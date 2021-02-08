import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CampoTreinamentoAlert{	
	private static WebDriver driver;
	@Before
	public void inicializar() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
	}
	@After
	public void fecharBrowser() {
		driver.quit();
	}
	@Test
	public void usarAlertSimples() {
		driver.findElement(By.id("alert")).click();
		Alert alerta = driver.switchTo().alert();
		String pegarTextAlert = alerta.getText();
		Assert.assertEquals("Alert Simples", pegarTextAlert);
		alerta.accept();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(pegarTextAlert);
	}
	@Test
	public void usarAlertConfirm() {
		driver.findElement(By.id("confirm")).click();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alerta.getText());
		alerta.accept();
		Assert.assertEquals("Confirmado", alerta.getText());
		alerta.accept();
	}
	@Test
	public void usarAlertCancelar() {
		driver.findElement(By.id("confirm")).click();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alerta.getText());
		alerta.dismiss();
		Assert.assertEquals("Negado", alerta.getText());
		alerta.accept();
	}
	@Test
	public void usarAlertPromptConfirmando() {
		driver.findElement(By.id("prompt")).click();
		Alert alerta = driver.switchTo().alert();
		String numero = "123";
		alerta.sendKeys(numero);
		alerta.accept();
		Assert.assertEquals("Era "+numero+"?", alerta.getText());
		alerta.accept();
		Assert.assertEquals(":D", alerta.getText());
		alerta.accept();
		}
	@Test
	public void usarAlertPromptNaoConfirmando() {
		driver.findElement(By.id("prompt")).click();
		Alert alerta = driver.switchTo().alert();
		String numero = "123";
		alerta.sendKeys(numero);
		alerta.accept();
		Assert.assertEquals("Era "+numero+"?", alerta.getText());
		alerta.dismiss();
		Assert.assertEquals(":(", alerta.getText());
		alerta.accept();
	}
}
