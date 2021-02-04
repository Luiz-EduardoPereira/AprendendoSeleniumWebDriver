import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class CampoTreinamentoIframeEJanelas{
	public static WebDriver driver;
	public void inicializar() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
	}
	@Test
	public void clicarBtnDetroFrame() {
		inicializar();
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Frame OK!", alerta.getText());
		alerta.accept();
		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Frame button OK!");
		Assert.assertEquals("Frame button OK!", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
	}
	@Test
	public void clicarAbrirPopup() {
		inicializar();
		String msgPopup= "Teste popup";
		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys(msgPopup);
		Assert.assertEquals(msgPopup, driver.findElement(By.tagName("textarea")).getAttribute("value"));
		driver.close();
		driver.switchTo().window("");
		driver.findElement(By.tagName("textarea")).sendKeys(msgPopup);
		Assert.assertEquals(msgPopup, driver.findElement(By.tagName("textarea")).getAttribute("value"));
	}
	@Test
	public void clicarPopupDoMal() {
		inicializar();
		String msgPopup= "Teste popup";
		driver.findElement(By.id("buttonPopUpHard")).click();
		driver.switchTo().window((String)driver.getWindowHandles().toArray()[1]);
		driver.findElement(By.tagName("textarea")).sendKeys(msgPopup);
		driver.switchTo().window((String)driver.getWindowHandles().toArray()[0]);
		driver.findElement(By.tagName("textarea")).sendKeys(msgPopup);
		}
}
