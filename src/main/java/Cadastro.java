import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Cadastro{
	public static WebDriver driver;
	public void inicialiazar() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
	}
	@Test
	public void realizarCadastro() {
		inicialiazar();
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
		Assert.assertEquals("Nome: Luiz", driver.findElement(By.id("descNome")).getText());
		Assert.assertEquals("Sobrenome: Eduardo", driver.findElement(By.id("descSobrenome")).getText());
		Assert.assertEquals("Sexo: Masculino", driver.findElement(By.id("descSexo")).getText());
		Assert.assertEquals("Comida: Carne", driver.findElement(By.id("descComida")).getText());
		Assert.assertEquals("Escolaridade: superior", driver.findElement(By.id("descEscolaridade")).getText());
		Assert.assertEquals("Esportes: Futebol", driver.findElement(By.id("descEsportes")).getText());
		Assert.assertEquals("Sugestoes: Devo aprender a fazer automatização com o Selenium WebDriver.", driver.findElement(By.id("descSugestoes")).getText());
	}
	@Test
	public void realizarCadastroComOpcoesDiversas() {
		inicialiazar();
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

