import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CampoTreinamentoRegraNegocio {
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
	public void btnCadastrar() {
		dsl.clicar(By.id("elementosForm:cadastrar"));
	}
	public void regraNegocioNome() {
		btnCadastrar();
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoAceitar());
		dsl.escrever("elementosForm:nome", "Usxowu");
	}
	public void regraNegocioSobrenome() {
		btnCadastrar();
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoAceitar());
		dsl.escrever("elementosForm:sobrenome", "Laostmodea");
	}
	public void regraNegocioSexo() {
		btnCadastrar();
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoAceitar());
		dsl.clicar(By.id("elementosForm:sexo:0"));
	}
	public void regraNegocioComida() {
		dsl.clicar(By.id("elementosForm:comidaFavorita:0"));
		dsl.clicar(By.id("elementosForm:comidaFavorita:3"));
		btnCadastrar();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoAceitar());
		dsl.clicar(By.id("elementosForm:comidaFavorita:3"));
	}
	public void regraNegocioEsporte() {
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		btnCadastrar();
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoAceitar());
		dsl.desmarcarCombo("elementosForm:esportes", "O que eh esporte?");
	}
	@Test
	public void testarRegraNegocio() {
		regraNegocioNome();
		regraNegocioSobrenome();
		regraNegocioSexo();
		regraNegocioComida();
		regraNegocioEsporte();
		btnCadastrar();
	}
}
