import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Cadastro{
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
	public void verificarCamposObrigatorios() {
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alertaNome = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alertaNome.getText());
		alertaNome.accept();
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Daceos");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alertaSobrenome = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alertaSobrenome.getText());
		alertaSobrenome.accept();
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Clicen");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alertaSexo = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alertaSexo.getText());
		alertaSobrenome.accept();
		driver.findElement(By.id("elementosForm:sexo:1")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
	}
	@Test
	public void realizarCadastro() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Luiz");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Eduardo");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		WebElement elementoComboUnico = driver.findElement(By.id("elementosForm:escolaridade"));
		Select comboUnico = new Select(elementoComboUnico);
		comboUnico.selectByVisibleText("Superior");
		WebElement elementoComboMultiplo = driver.findElement(By.id("elementosForm:esportes"));
		Select comboMultiplo = new Select(elementoComboMultiplo);
		comboMultiplo.selectByVisibleText("Futebol");
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Devo aprender a fazer automatização com o Selenium WebDriver.");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		validarCadastro();
	}
	public void validarCadastro() {
		Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
		Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Luiz"));
		Assert.assertEquals("Sobrenome: Eduardo", driver.findElement(By.id("descSobrenome")).getText());
		Assert.assertEquals("Sexo: Masculino", driver.findElement(By.id("descSexo")).getText());
		Assert.assertEquals("Comida: Carne", driver.findElement(By.id("descComida")).getText());
		Assert.assertEquals("Escolaridade: superior", driver.findElement(By.id("descEscolaridade")).getText());
		Assert.assertEquals("Esportes: Futebol", driver.findElement(By.id("descEsportes")).getText());
		Assert.assertEquals("Sugestoes: Devo aprender a fazer automatização com o Selenium WebDriver.", driver.findElement(By.id("descSugestoes")).getText());
	}
	@Test
	public void realizarCadastroComOpcoesDiversas() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Luiz");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Eduardo");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		WebElement elementoComboUnico = driver.findElement(By.id("elementosForm:escolaridade"));
		Select comboUnico = new Select(elementoComboUnico);
		comboUnico.selectByVisibleText("Superior");
		WebElement elementoComboMultiplo = driver.findElement(By.id("elementosForm:esportes"));
		Select comboMultiplo = new Select(elementoComboMultiplo);
		comboMultiplo.selectByVisibleText("Futebol");
		comboMultiplo.selectByVisibleText("Natacao");
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Devo aprender a fazer automatização com o Selenium WebDriver.");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		validarCadastroMultiplo();
	}
	public void validarCadastroMultiplo() {
		Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
		Assert.assertEquals("Nome: Luiz", driver.findElement(By.id("descNome")).getText());
		Assert.assertEquals("Sobrenome: Eduardo", driver.findElement(By.id("descSobrenome")).getText());
		Assert.assertEquals("Sexo: Masculino", driver.findElement(By.id("descSexo")).getText());
		Assert.assertEquals("Comida: Carne Pizza", driver.findElement(By.id("descComida")).getText());
		Assert.assertEquals("Escolaridade: superior", driver.findElement(By.id("descEscolaridade")).getText());
		Assert.assertEquals("Esportes: Natacao Futebol", driver.findElement(By.id("descEsportes")).getText());
		Assert.assertEquals("Sugestoes: Devo aprender a fazer automatização com o Selenium WebDriver.", driver.findElement(By.id("descSugestoes")).getText());
	}
}

