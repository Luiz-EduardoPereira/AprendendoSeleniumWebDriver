import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
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
		dsl.clicarBtn("elementosForm:cadastrar");
	}
	public void regraNegocioNome() {
		btnCadastrar();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alerta.getText());
		alerta.accept();
		dsl.escreve("elementosForm:nome", "Usxowu");
	}
	public void regraNegocioSobrenome() {
		btnCadastrar();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alerta.getText());
		alerta.accept();
		dsl.escreve("elementosForm:sobrenome", "Laostmodea");
	}
	public void regraNegocioSexo() {
		btnCadastrar();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alerta.getText());
		alerta.accept();
		dsl.clicarRadio("elementosForm:sexo:0");
	}
	public void regraNegocioComida() {
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
		dsl.clicarRadio("elementosForm:comidaFavorita:3");
		btnCadastrar();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alerta.getText());
		alerta.accept();
		dsl.clicarRadio("elementosForm:comidaFavorita:3");
	}
	public void regraNegocioEsporte() {
		dsl.selecionarComboBox("elementosForm:esportes", "Natacao");
		dsl.selecionarComboBox("elementosForm:esportes", "O que eh esporte?");
		btnCadastrar();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Voce faz esporte ou nao?", alerta.getText());
		alerta.accept();
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
