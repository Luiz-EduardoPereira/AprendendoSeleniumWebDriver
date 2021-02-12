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
	public void usarTextField() {
		dsl.escrever("elementosForm:nome", "Wies");
		dsl.escrever("elementosForm:sobrenome", "Nuyhe");
		Assert.assertEquals("Wies", dsl.obterValorCampo("elementosForm:nome"));
		Assert.assertEquals("Nuyhe", dsl.obterValorCampo("elementosForm:sobrenome"));
	}
	@Test
	public void limparTextField() {
		dsl.escrever("elementosForm:nome", "Wies");
		dsl.escrever("elementosForm:sobrenome", "Nuyhe");
		dsl.limparText("elementosForm:nome");
		dsl.limparText("elementosForm:sobrenome");
		Assert.assertEquals("", dsl.obterValorCampo("elementosForm:nome"));
		Assert.assertEquals("", dsl.obterValorCampo("elementosForm:sobrenome"));
	}
	@Test
	public void usarTextArea() {
		dsl.escrever("elementosForm:sugestoes", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. ");
		Assert.assertEquals("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. ", dsl.obterValorCampo("elementosForm:sugestoes"));
	}
	@Test
	public void usarTextAreaComQuebraLinha() {
		dsl.escrever("elementosForm:sugestoes", "Agile\nQA\nPO\nDEV");
		Assert.assertEquals("Agile\nQA\nPO\nDEV", dsl.obterValorCampo("elementosForm:sugestoes"));
		}
	@Test
	public void limparTextArea() {
		dsl.escrever("elementosForm:sugestoes", "Agile\nQA\nPO\nDEV");
		dsl.limparText("elementosForm:sugestoes");
		Assert.assertEquals("", dsl.obterValorCampo("elementosForm:sugestoes"));
	}
	@Test
	public void usarRadioButton() {
		dsl.clicar(By.id("elementosForm:sexo:0"));
		Assert.assertTrue(dsl.checarMarcacao("elementosForm:sexo:0"));
	}
	@Test
	public void usarRadioButtonAlternando() {
		dsl.clicar(By.id("elementosForm:sexo:0"));
		dsl.clicar(By.id("elementosForm:sexo:1"));
		Assert.assertTrue(dsl.checarMarcacao("elementosForm:sexo:1"));
	}
	@Test
	public void usarCheckBox() {
		dsl.clicar(By.id("elementosForm:comidaFavorita:0"));
		Assert.assertTrue(dsl.checarMarcacao("elementosForm:comidaFavorita:0"));
	}
	@Test
	public void usarCheckBoxMultiplo() {
		dsl.clicar(By.id("elementosForm:comidaFavorita:0"));
		dsl.clicar(By.id("elementosForm:comidaFavorita:1"));
		dsl.clicar(By.id("elementosForm:comidaFavorita:2"));
		dsl.checarMarcacao("elementosForm:comidaFavorita:0");
		dsl.checarMarcacao("elementosForm:comidaFavorita:1");
		dsl.checarMarcacao("elementosForm:comidaFavorita:2");
	}
	@Test
	public void limparCheckBox() {
		dsl.clicar(By.id("elementosForm:comidaFavorita:0"));
		dsl.clicar(By.id("elementosForm:comidaFavorita:0"));
		Assert.assertFalse(dsl.checarMarcacao("elementosForm:comidaFavorita:0"));
	}
	@Test
	public void pegarQuantidadeOpcoesComboBoxEscolaridade() {
		Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
	}
	@Test
	public void usarComboBoxSelecionarIndex() {
		WebElement elemento = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(elemento);
		combo.selectByIndex(4);
		Assert.assertEquals("Superior", dsl.obterValorCombo("elementosForm:escolaridade"));
	}
	@Test
	public void usarComboBoxSelecionarValue() {
		WebElement elemento = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(elemento);
		combo.selectByValue("1grauincomp");
		Assert.assertEquals("1o grau incompleto", dsl.obterValorCombo("elementosForm:escolaridade"));
	}
	@Test
	public void usarComboBoxSelecionarVisibleText() {
		dsl.selecionarCombo("elementosForm:escolaridade", "Doutorado");
		Assert.assertEquals("Doutorado", dsl.obterValorCombo("elementosForm:escolaridade"));
	}
	@Test
	public void pegarQuantidadeOpcoesComboBoxEsportes() {
		Assert.assertEquals(5, dsl.obterQuantidadeOpcoesCombo("elementosForm:esportes"));
	}
	@Test
	public void usarComboBoxMultiplo() {
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "Futebol");
		Assert.assertEquals(3, dsl.obterQuantidadeCombo("elementosForm:esportes"));
	}
	@Test
	public void desmarcarTodosComboBox() {
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "Futebol");
		dsl.desmarcarTodosCombo("elementosForm:esportes");
		Assert.assertEquals(0, dsl.obterQuantidadeCombo("elementosForm:esportes"));
	}
	@Test
	public void desmarcarUmComboBox() {
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.desmarcarCombo("elementosForm:esportes", "Natacao");
		Assert.assertEquals(1, dsl.obterQuantidadeCombo("elementosForm:esportes"));
	}
	@Test
	public void clicarBotaoCliqueMe() {
		dsl.clicar(By.id("buttonSimple"));
		Assert.assertEquals("Obrigado!", dsl.obterValorCampo("buttonSimple"));
	}
	@Test
	public void usarLinkVoltar() {
		dsl.clicar(By.linkText("Voltar"));
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}
	@Test
	public void buscarTextNaPagina() {
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
	}
}
