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
	private DSL dsl;
	@Before
	public void inicializar() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}
	@After
	public void fecharBrowser() {
		driver.quit();
	}
	@Test
	public void usarAlertSimples() {
		dsl.clicarBtn("alert");
		Alert alerta = driver.switchTo().alert();
		String pegarTextAlert = alerta.getText();
		Assert.assertEquals("Alert Simples", pegarTextAlert);
		alerta.accept();
		dsl.escreve("elementosForm:nome", pegarTextAlert);
	}
	@Test
	public void usarAlertConfirm() {
		dsl.clicarBtn("confirm");
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alerta.getText());
		alerta.accept();
		Assert.assertEquals("Confirmado", alerta.getText());
		alerta.accept();
	}
	@Test
	public void usarAlertCancelar() {
		dsl.clicarBtn("confirm");
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alerta.getText());
		alerta.dismiss();
		Assert.assertEquals("Negado", alerta.getText());
		alerta.accept();
	}
	@Test
	public void usarAlertPromptConfirmando() {
		dsl.clicarBtn("prompt");
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
		dsl.clicarBtn("prompt");
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
