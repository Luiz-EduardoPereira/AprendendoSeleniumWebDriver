import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Cadastro{
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
	public void verificarCamposObrigatorios() {
		dsl.clicarBtn("elementosForm:cadastrar");
		Alert alertaNome = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alertaNome.getText());
		alertaNome.accept();
		dsl.escreve("elementosForm:nome", "Daceos");
		dsl.clicarBtn("elementosForm:cadastrar");
		Alert alertaSobrenome = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alertaSobrenome.getText());
		alertaSobrenome.accept();
		dsl.escreve("elementosForm:sobrenome", "Clicen");
		dsl.clicarBtn("elementosForm:cadastrar");
		Alert alertaSexo = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alertaSexo.getText());
		alertaSobrenome.accept();
		dsl.clicarBtn("elementosForm:sexo:1");
		dsl.clicarBtn("elementosForm:cadastrar");
	}
	@Test
	public void realizarCadastro() {
		dsl.escreve("elementosForm:nome", "Luiz");
		dsl.escreve("elementosForm:sobrenome", "Eduardo");
		dsl.clicarBtn("elementosForm:sexo:0");
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
		dsl.selecionarComboBox("elementosForm:escolaridade", "Superior");
		dsl.selecionarComboBox("elementosForm:esportes", "Futebol");
		dsl.escreve("elementosForm:sugestoes", "Devo aprender a fazer automatização com o Selenium WebDriver.");
		dsl.clicarBtn("elementosForm:cadastrar");
		validarCadastro();
	}
	public void validarCadastro() {
		//Assert.assertTrue(dsl.obterTexto("resultado")).getText().startsWith("Cadastrado!"));
		//Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Luiz"));
		Assert.assertEquals("Sobrenome: Eduardo", dsl.obterTexto("descSobrenome"));
		Assert.assertEquals("Sexo: Masculino", dsl.obterTexto("descSexo"));
		Assert.assertEquals("Comida: Carne", dsl.obterTexto("descComida"));
		Assert.assertEquals("Escolaridade: superior", dsl.obterTexto("descEscolaridade"));
		Assert.assertEquals("Esportes: Futebol", dsl.obterTexto("descEsportes"));
		Assert.assertEquals("Sugestoes: Devo aprender a fazer automatização com o Selenium WebDriver.", dsl.obterTexto("descSugestoes"));
	}
	@Test
	public void realizarCadastroComOpcoesDiversas() {
		dsl.escreve("elementosForm:nome", "Luiz");
		dsl.escreve("elementosForm:sobrenome", "Eduardo");
		dsl.clicarRadio("elementosForm:sexo:0");
		dsl.clicarCheckBox("elementosForm:comidaFavorita:0");
		dsl.clicarCheckBox("elementosForm:comidaFavorita:2");
		dsl.selecionarComboBox("elementosForm:escolaridade", "Superior");
		dsl.selecionarComboBox("elementosForm:esportes", "Futebol");
		dsl.selecionarComboBox("elementosForm:esportes", "Natacao");
		dsl.escreve("elementosForm:sugestoes", "Devo aprender a fazer automatização com o Selenium WebDriver.");
		dsl.clicarBtn("elementosForm:cadastrar");
		validarCadastroMultiplo();
	}
	public void validarCadastroMultiplo() {
		//Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
		Assert.assertEquals("Nome: Luiz", dsl.obterTexto("descNome"));
		Assert.assertEquals("Sobrenome: Eduardo", dsl.obterTexto("descSobrenome"));
		Assert.assertEquals("Sexo: Masculino", dsl.obterTexto("descSexo"));
		Assert.assertEquals("Comida: Carne Pizza", dsl.obterTexto("descComida"));
		Assert.assertEquals("Escolaridade: superior", dsl.obterTexto("descEscolaridade"));
		Assert.assertEquals("Esportes: Natacao Futebol", dsl.obterTexto("descEsportes"));
		Assert.assertEquals("Sugestoes: Devo aprender a fazer automatização com o Selenium WebDriver.", dsl.obterTexto("descSugestoes"));
	}
}

