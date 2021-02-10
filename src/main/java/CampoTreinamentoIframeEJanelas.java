import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CampoTreinamentoIframeEJanelas{
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
	public void clicarBtnDetroFrame() {
		driver.switchTo().frame("frame1");
		dsl.clicar(By.id("frameButton"));
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Frame OK!", alerta.getText());
		alerta.accept();
		driver.switchTo().defaultContent();
		dsl.escreve("elementosForm:nome", "Frame button OK!");
		Assert.assertEquals("Frame button OK!", dsl.obterTextoValue("elementosForm:nome"));
	}
	@Test
	public void clicarAbrirPopup() {
		String msgPopup = "Teste popup";
		dsl.clicar(By.id("buttonPopUpEasy"));
		driver.switchTo().window("Popup");
		dsl.escreve(By.tagName("textarea"), msgPopup);
		driver.close();
		driver.switchTo().window("");
		dsl.escreve("elementosForm:sugestoes", msgPopup);
		Assert.assertEquals(msgPopup, dsl.obterTextoValue("elementosForm:sugestoes"));
	}
	@Test
	public void clicarPopupDoMal() {
		String msgPopup= "Teste popup";
		dsl.clicar(By.id("buttonPopUpHard"));
		driver.switchTo().window((String)driver.getWindowHandles().toArray()[1]);
		dsl.escreve(By.tagName("textarea"), msgPopup);
		driver.switchTo().window((String)driver.getWindowHandles().toArray()[0]);
		dsl.escreve(By.tagName("textarea"), msgPopup);
		Assert.assertEquals(msgPopup, dsl.obterTextoValue("elementosForm:sugestoes"));
		}
}
