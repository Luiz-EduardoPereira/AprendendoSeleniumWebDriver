import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Cadastro{
	private static WebDriver driver;
	private CampoTreinamentoPage page;
	@Before
	public void inicializar() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage(driver);
	}
	@After
	public void fecharBrowser() {
		driver.quit();
	}
	@Test
	public void verificarCamposObrigatorios() {
		page.cadastrar();
		Assert.assertEquals("Nome eh obrigatorio", page.obterTextoAlertEAceitar());
		page.inserirNome("Daceos");
		page.cadastrar();
		Assert.assertEquals("Sobrenome eh obrigatorio", page.obterTextoAlertEAceitar());
		page.inserirSobrenome("Clicen");
		page.cadastrar();
		Assert.assertEquals("Sexo eh obrigatorio", page.obterTextoAlertEAceitar());
		page.setSexoMasculino();
		page.cadastrar();
	}
	@Test
	public void realizarCadastro() {
		page.inserirNome("Luiz");
		page.inserirSobrenome("Eduardo");
		page.setSexoMasculino();
		page.setComidaPizza();
		page.setEscolaridade("Superior");
		page.setEsporte("Futebol");
		page.inserirSugestao("Devo aprender a fazer automatização com o Selenium WebDriver.");
		page.cadastrar();
		validarCadastro();
	}
	public void validarCadastro() {
		Assert.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
		Assert.assertTrue(page.obterNomeCadastrado().endsWith("Luiz"));
		Assert.assertEquals("Sobrenome: Eduardo", page.obterSobrenomeCadastrado());
		Assert.assertEquals("Sexo: Masculino", page.obterSexoCadastrado());
		Assert.assertEquals("Comida: Pizza", page.obterComidaCadastrada());
		Assert.assertEquals("Escolaridade: superior",  page.obterEscolaridadeCadastrada());
		Assert.assertEquals("Esportes: Futebol", page.obterEsporteCadastrado());
		Assert.assertEquals("Sugestoes: Devo aprender a fazer automatização com o Selenium WebDriver.", page.obterSugestaoCadastrada());
	}
	@Test
	public void realizarCadastroComOpcoesDiversas() {
		page.inserirNome("Luiz");
		page.inserirSobrenome("Eduardo");
		page.setSexoMasculino();
		page.setComidaPizza();
		page.setComidaCarne();
		page.setEscolaridade("Superior");
		page.setEsporte("Futebol","Natacao");
		page.inserirSugestao("Devo aprender a fazer automatização com o Selenium WebDriver.");
		page.cadastrar();
		validarCadastroMultiplo();
	}
	public void validarCadastroMultiplo() {
		Assert.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
		Assert.assertTrue(page.obterNomeCadastrado().endsWith("Luiz"));
		Assert.assertEquals("Sobrenome: Eduardo", page.obterSobrenomeCadastrado());
		Assert.assertEquals("Sexo: Masculino", page.obterSexoCadastrado());
		Assert.assertEquals("Comida: Carne Pizza", page.obterComidaCadastrada());
		Assert.assertEquals("Escolaridade: superior", page.obterEscolaridadeCadastrada());
		Assert.assertEquals("Esportes: Natacao Futebol", page.obterEsporteCadastrado());
		Assert.assertEquals("Sugestoes: Devo aprender a fazer automatização com o Selenium WebDriver.", page.obterSugestaoCadastrada());
	}
}

