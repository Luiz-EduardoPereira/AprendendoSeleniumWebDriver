import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
		dsl.entrarFrame("frame1");
		dsl.clicar(By.id("frameButton"));
		Assert.assertEquals("Frame OK!", dsl.alertaObterTextoAceitar());
		dsl.sairFrame();
		dsl.escrever("elementosForm:nome", "Frame button OK!");
		Assert.assertEquals("Frame button OK!", dsl.obterValorCampo("elementosForm:nome"));
	}
	@Test
	public void clicarFrameEscondido() {
		WebElement frame = driver.findElement(By.id("frame2"));
		dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
		dsl.entrarFrame("frame2");
		dsl.clicar(By.id("frameButton"));
		Assert.assertEquals("Frame OK!", dsl.alertaObterTextoAceitar());
	}
	@Test
	public void clicarAbrirPopup() {
		String msgPopup = "Teste popup";
		dsl.clicar(By.id("buttonPopUpEasy"));
		dsl.trocarJanela("Popup");
		dsl.escrever(By.tagName("textarea"), msgPopup);
		driver.close();
		dsl.trocarJanela("");
		dsl.escrever("elementosForm:sugestoes", msgPopup);
		Assert.assertEquals(msgPopup, dsl.obterValorCampo("elementosForm:sugestoes"));
	}
	@Test
	public void clicarPopupDoMal() {
		String msgPopup= "Teste popup";
		dsl.clicar(By.id("buttonPopUpHard"));
		String janelaFilho = (String) driver.getWindowHandles().toArray()[1];
		String janelaPai = (String) driver.getWindowHandles().toArray()[0];
		dsl.trocarJanela(janelaFilho);
		dsl.escrever(By.tagName("textarea"), msgPopup);
		dsl.trocarJanela(janelaPai);
		dsl.escrever(By.tagName("textarea"), msgPopup);
		Assert.assertEquals(msgPopup, dsl.obterValorCampo("elementosForm:sugestoes"));
		}
}
