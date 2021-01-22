import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CampoTreinamento {
	WebDriver driver;
	public void inicializar() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
	}
	@Test
	public void usarTextField() {
		inicializar();
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Wies");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Nuyhe");
		Assert.assertEquals("Wies", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		Assert.assertEquals("Nuyhe", driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));
	}
	@Test
	public void usarTextArea() {
		inicializar();
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. ");
		Assert.assertEquals("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. " , driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
	}
	@Test
	public void usarTextAreaComQuebraLinha() {
		inicializar();
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Agile\nQA\nPO\nDEV");
		Assert.assertEquals("Agile\nQA\nPO\nDEV", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
	}
}
