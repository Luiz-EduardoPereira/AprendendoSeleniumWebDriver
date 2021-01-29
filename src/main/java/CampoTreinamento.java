import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CampoTreinamento {
	private static WebDriver driver;
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
	public void limparTextField() {
		inicializar();
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Wies");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Nuyhe");
		driver.findElement(By.id("elementosForm:nome")).clear();
		driver.findElement(By.id("elementosForm:sobrenome")).clear();
		Assert.assertEquals("", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		Assert.assertEquals("", driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));
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
	@Test
	public void limparTextArea() {
		inicializar();
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Agile\nQA\nPO\nDEV");
		driver.findElement(By.id("elementosForm:sugestoes")).clear();
		Assert.assertEquals("", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
	}
	@Test
	public void usarRadioButton() {
		inicializar();
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
	}
	@Test
	public void usarRadioButtonAlternando() {
		inicializar();
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:sexo:1")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:1")).isSelected());
	}
	@Test
	public void usarCheckBox() {
		inicializar();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());
	}
	@Test
	public void usarCheckBoxMultiplo() {
		inicializar();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();		
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:1")).isSelected());	
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
	}
	@Test
	public void limparCheckBox() {
		inicializar();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		Assert.assertFalse(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());
	}
	@Test
	public void usarComboBoxSelecionarIndex() {
		inicializar();
		WebElement elemento = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(elemento);
		combo.selectByIndex(4);
		Assert.assertEquals("Superior", combo.getFirstSelectedOption().getText());
	}
	@Test
	public void usarComboBoxSelecionarValue() {
		inicializar();
		WebElement elemento = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(elemento);
		combo.selectByValue("1grauincomp");
		Assert.assertEquals("1o grau incompleto", combo.getFirstSelectedOption().getText());
	}
	@Test
	public void usarComboBoxSelecionarVisibleText() {
		inicializar();
		WebElement elemento = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(elemento);
		combo.selectByVisibleText("Doutorado");
		Assert.assertEquals("Doutorado", combo.getFirstSelectedOption().getText());
	}
	@Test
	public void usarComboBoxMultiplo() {
		inicializar();
		WebElement elemento = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(elemento);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("Futebol");
		List<WebElement> listaComboBox = combo.getAllSelectedOptions();
		Assert.assertEquals("3", listaComboBox);
	}
}
