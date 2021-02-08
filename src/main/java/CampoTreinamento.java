import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CampoTreinamento {
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
	public void usarTextField() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Wies");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Nuyhe");
		Assert.assertEquals("Wies", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		Assert.assertEquals("Nuyhe", driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));
	}
	@Test
	public void limparTextField() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Wies");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Nuyhe");
		driver.findElement(By.id("elementosForm:nome")).clear();
		driver.findElement(By.id("elementosForm:sobrenome")).clear();
		Assert.assertEquals("", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		Assert.assertEquals("", driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));
	}
	@Test
	public void usarTextArea() {
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. ");
		Assert.assertEquals("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. " , driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
	}
	@Test
	public void usarTextAreaComQuebraLinha() {
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Agile\nQA\nPO\nDEV");
		Assert.assertEquals("Agile\nQA\nPO\nDEV", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
	}
	@Test
	public void limparTextArea() {
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Agile\nQA\nPO\nDEV");
		driver.findElement(By.id("elementosForm:sugestoes")).clear();
		Assert.assertEquals("", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
	}
	@Test
	public void usarRadioButton() {
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
	}
	@Test
	public void usarRadioButtonAlternando() {
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:sexo:1")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:1")).isSelected());
	}
	@Test
	public void usarCheckBox() {
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());
	}
	@Test
	public void usarCheckBoxMultiplo() {
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();		
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:1")).isSelected());	
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
	}
	@Test
	public void limparCheckBox() {
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		Assert.assertFalse(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());
	}
	@Test
	public void pegarQuantidadeOpcoesComboBoxEscolaridade() {
		WebElement elemento = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(elemento);
		List<WebElement> listaOpcoes = combo.getOptions();
		Assert.assertEquals(8, listaOpcoes.size());
	}
	@Test
	public void usarComboBoxSelecionarIndex() {
		WebElement elemento = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(elemento);
		combo.selectByIndex(4);
		Assert.assertEquals("Superior", combo.getFirstSelectedOption().getText());
	}
	@Test
	public void usarComboBoxSelecionarValue() {
		WebElement elemento = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(elemento);
		combo.selectByValue("1grauincomp");
		Assert.assertEquals("1o grau incompleto", combo.getFirstSelectedOption().getText());
	}
	@Test
	public void usarComboBoxSelecionarVisibleText() {
		WebElement elemento = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(elemento);
		combo.selectByVisibleText("Doutorado");
		Assert.assertEquals("Doutorado", combo.getFirstSelectedOption().getText());
	}
	@Test
	public void pegarQuantidadeOpcoesComboBoxEsportes() {
		WebElement elemento = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(elemento);
		List<WebElement> listaOpcoes = combo.getOptions();
		Assert.assertEquals(5, listaOpcoes.size());
	}
	@Test
	public void usarComboBoxMultiplo() {
		WebElement elemento = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(elemento);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("Futebol");
		List<WebElement> listaComboBox = combo.getAllSelectedOptions();
		Assert.assertEquals(3, listaComboBox.size());
	}
	@Test
	public void desmarcarTodosComboBox() {
		WebElement elemento = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(elemento);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.deselectAll();
		List<WebElement> listaComboBox = combo.getAllSelectedOptions();
		Assert.assertEquals(0, listaComboBox.size());
	}
	@Test
	public void desmarcarUmComboBox() {
		WebElement elemento = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(elemento);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.deselectByVisibleText("Corrida");
		Assert.assertEquals("Natacao", combo.getFirstSelectedOption().getText());
	}
	@Test
	public void clicarBotaoCliqueMe() {
		driver.findElement(By.id("buttonSimple")).click();
		Assert.assertEquals("Obrigado!", driver.findElement(By.id("buttonSimple")).getAttribute("value"));
	}
	@Test
	public void usarLinkVoltar() {
		driver.findElement(By.linkText("Voltar")).click();
		Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());
	}
	@Test
	public void buscarTextNaPagina() {
		Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", driver.findElement(By.className("facilAchar")).getText());
	}
}
