import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
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
		dsl.clicar(By.id("elementosForm:cadastrar"));
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoAceitar());
		dsl.escrever("elementosForm:nome", "Daceos");
		dsl.clicar(By.id("elementosForm:cadastrar"));
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoAceitar());
		dsl.escrever("elementosForm:sobrenome", "Clicen");
		dsl.clicar(By.id("elementosForm:cadastrar"));
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoAceitar());
		dsl.clicar(By.id("elementosForm:sexo:1"));
		dsl.clicar(By.id("elementosForm:cadastrar"));
	}
	@Test
	public void realizarCadastro() {
		dsl.escrever("elementosForm:nome", "Luiz");
		dsl.escrever("elementosForm:sobrenome", "Eduardo");
		dsl.clicar(By.id("elementosForm:cadastrar"));
		dsl.clicar(By.id("elementosForm:comidaFavorita:0"));
		dsl.selecionarCombo("elementosForm:escolaridade", "Superior");
		dsl.selecionarCombo("elementosForm:esportes", "Futebol");
		dsl.escrever("elementosForm:sugestoes", "Devo aprender a fazer automatização com o Selenium WebDriver.");
		dsl.clicar(By.id("elementosForm:cadastrar"));
		validarCadastro();
	}
	public void validarCadastro() {
		Assert.assertTrue(dsl.obterComecoTextoBoolean("resultado", "Cadastrado!"));
		Assert.assertEquals("Nome: Luiz", dsl.obterTexto("descNome"));
		Assert.assertEquals("Sobrenome: Eduardo", dsl.obterTexto("descSobrenome"));
		Assert.assertEquals("Sexo: Masculino", dsl.obterTexto("descSexo"));
		Assert.assertEquals("Comida: Carne", dsl.obterTexto("descComida"));
		Assert.assertEquals("Escolaridade: superior",  dsl.obterTexto("descEscolaridade"));
		Assert.assertEquals("Esportes: Futebol", dsl.obterTexto("descEsportes"));
		Assert.assertEquals("Sugestoes: Devo aprender a fazer automatização com o Selenium WebDriver.", dsl.obterTexto("descSugestoes"));
	}
	@Test
	public void realizarCadastroComOpcoesDiversas() {
		dsl.escrever("elementosForm:nome", "Luiz");
		dsl.escrever("elementosForm:sobrenome", "Eduardo");
		dsl.clicar(By.id("elementosForm:sexo:0"));
		dsl.clicar(By.id("elementosForm:comidaFavorita:0"));
		dsl.clicar(By.id("elementosForm:comidaFavorita:2"));
		dsl.selecionarCombo("elementosForm:escolaridade", "Superior");
		dsl.selecionarCombo("elementosForm:esportes", "Futebol");
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.escrever("elementosForm:sugestoes", "Devo aprender a fazer automatização com o Selenium WebDriver.");
		dsl.clicar(By.id("elementosForm:cadastrar"));
		validarCadastroMultiplo();
	}
	public void validarCadastroMultiplo() {
		Assert.assertTrue(dsl.obterComecoTextoBoolean("resultado", "Cadastrado!"));
		Assert.assertEquals("Nome: Luiz", dsl.obterTexto("descNome"));
		Assert.assertEquals("Sobrenome: Eduardo", dsl.obterTexto("descSobrenome"));
		Assert.assertEquals("Sexo: Masculino", dsl.obterTexto("descSexo"));
		Assert.assertEquals("Comida: Carne Pizza", dsl.obterTexto("descComida"));
		Assert.assertEquals("Escolaridade: superior", dsl.obterTexto("descEscolaridade"));
		Assert.assertEquals("Esportes: Natacao Futebol", dsl.obterTexto("descEsportes"));
		Assert.assertEquals("Sugestoes: Devo aprender a fazer automatização com o Selenium WebDriver.", dsl.obterTexto("descSugestoes"));
	}
}

