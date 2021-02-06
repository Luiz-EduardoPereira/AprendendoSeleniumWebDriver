import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CampoTreinamentoRegraNegocio {
	public static WebDriver driver;
	public void inicialiazar() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
	}
	@Test
	public void testarRegraNegocio() {
		driver.findElement(By.id("")).click();
	}
}
