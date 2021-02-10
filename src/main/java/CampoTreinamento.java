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
		dsl.escreve("elementosForm:nome", "Wies");
		dsl.escreve("elementosForm:sobrenome", "Nuyhe");
		Assert.assertEquals("Wies", dsl.obterValorCampo("elementosForm:nome"));
		Assert.assertEquals("Nuyhe", dsl.obterValorCampo("elementosForm:sobrenome"));
	}
	@Test
	public void limparTextField() {
		dsl.escreve("elementosForm:nome", "Wies");
		dsl.escreve("elementosForm:sobrenome", "Nuyhe");
		dsl.limparText("elementosForm:nome");
		dsl.limparText("elementosForm:sobrenome");
		Assert.assertEquals("", dsl.obterValorCampo("elementosForm:nome"));
		Assert.assertEquals("", dsl.obterValorCampo("elementosForm:sobrenome"));
	}
	@Test
	public void usarTextArea() {
		dsl.escreve("elementosForm:sugestoes", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. ");
		Assert.assertEquals("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. ", dsl.obterValorCampo("elementosForm:sugestoes"));
	}
	@Test
	public void usarTextAreaComQuebraLinha() {
		dsl.escreve("elementosForm:sugestoes", "Agile\nQA\nPO\nDEV");
		Assert.assertEquals("Agile\nQA\nPO\nDEV", dsl.obterValorCampo("elementosForm:sugestoes"));
		}
	@Test
	public void limparTextArea() {
		dsl.escreve("elementosForm:sugestoes", "Agile\nQA\nPO\nDEV");
		dsl.limparText("elementosForm:sugestoes");
		Assert.assertEquals("", dsl.obterValorCampo("elementosForm:sugestoes"));
	}
	@Test
	public void usarRadioButton() {
		dsl.clicar(By.id("elementosForm:sexo:0"));
		Assert.assertTrue(dsl.checarMarcacaoRadio("elementosForm:sexo:0"));
	}
	@Test
	public void usarRadioButtonAlternando() {
		dsl.clicar(By.id("elementosForm:sexo:0"));
		dsl.clicar(By.id("elementosForm:sexo:1"));
		Assert.assertTrue(dsl.checarMarcacaoRadio("elementosForm:sexo:1"));
	}
	@Test
	public void usarCheckBox() {
		dsl.clicar(By.id("elementosForm:comidaFavorita:0"));
		Assert.assertTrue(dsl.checarMarcacaoCheckBox("elementosForm:comidaFavorita:0"));
	}
	@Test
	public void usarCheckBoxMultiplo() {
		dsl.clicar(By.id("elementosForm:comidaFavorita:0"));
		dsl.clicar(By.id("elementosForm:comidaFavorita:1"));
		dsl.clicar(By.id("elementosForm:comidaFavorita:2"));
		dsl.checarMarcacaoCheckBox("elementosForm:comidaFavorita:0");
		dsl.checarMarcacaoCheckBox("elementosForm:comidaFavorita:1");
		dsl.checarMarcacaoCheckBox("elementosForm:comidaFavorita:2");
	}
	@Test
	public void limparCheckBox() {
		dsl.clicar(By.id("elementosForm:comidaFavorita:0"));
		dsl.clicar(By.id("elementosForm:comidaFavorita:0"));
		Assert.assertFalse(dsl.checarMarcacaoCheckBox("elementosForm:comidaFavorita:0"));
	}/*				PRECISA AJUSTAR
	@Test
	public void pegarQuantidadeOpcoesComboBoxEscolaridade() {
		WebElement elemento = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(elemento);
		List<WebElement> listaOpcoes = combo.getOptions();
		Assert.assertEquals(8, listaOpcoes.size());
	}*/
	/*@Test
	public void usarComboBoxSelecionarIndex() {
		WebElement elemento = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(elemento);
		combo.selectByIndex(4);
		Assert.assertEquals("Superior", combo.getFirstSelectedOption().getText());
	}*/
	/*@Test
	public void usarComboBoxSelecionarValue() {
		WebElement elemento = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(elemento);
		combo.selectByValue("1grauincomp");
		Assert.assertEquals("1o grau incompleto", combo.getFirstSelectedOption().getText());
	}*/
	@Test
	public void usarComboBoxSelecionarVisibleText() {
		dsl.selecionarComboBox("elementosForm:escolaridade", "Doutorado");
		Assert.assertEquals("Doutorado", dsl.obterValorCombo("elementosForm:escolaridade"));
	}
	/*@Test
	public void pegarQuantidadeOpcoesComboBoxEsportes() {
		WebElement elemento = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(elemento);
		List<WebElement> listaOpcoes = combo.getOptions();
		Assert.assertEquals(5, listaOpcoes.size());
	}*/
	@Test
	public void usarComboBoxMultiplo() {
		dsl.selecionarComboBox("elementosForm:esportes", "Natacao");
		dsl.selecionarComboBox("elementosForm:esportes", "Corrida");
		dsl.selecionarComboBox("elementosForm:esportes", "Futebol");
		WebElement elemento = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(elemento);
		List<WebElement> listaComboBox = combo.getAllSelectedOptions();
		Assert.assertEquals(3, listaComboBox.size());
	}
	@Test
	public void desmarcarTodosComboBox() {
		dsl.selecionarComboBox("elementosForm:esportes", "Natacao");
		dsl.selecionarComboBox("elementosForm:esportes", "Corrida");
		dsl.selecionarComboBox("elementosForm:esportes", "Futebol");
		dsl.desmarcarTodosCombo("elementosForm:esportes");
		WebElement elemento = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(elemento);
		List<WebElement> listaComboBox = combo.getAllSelectedOptions();
		Assert.assertEquals(0, listaComboBox.size());
	}
	@Test
	public void desmarcarUmComboBox() {
		dsl.selecionarComboBox("elementosForm:esportes", "Natacao");
		dsl.desmarcarCombo("elementosForm:esportes", "Natacao");
		//dsl.obterValorCombo("elementosForm:esportes");
		WebElement elemento = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(elemento);
		List<WebElement> listaComboBox = combo.getAllSelectedOptions();
		Assert.assertEquals(0, listaComboBox.size());
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
