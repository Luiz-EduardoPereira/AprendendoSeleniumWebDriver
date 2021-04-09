package br.ce.luiz.test;
import static br.ce.luiz.core.DriverFactory.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import br.ce.luiz.core.DriverFactory;

public class Cadastro{
	private CampoTreinamentoPage page;
	@Before
	public void inicializar() {
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage();
	}
	@After
	public void fecharBrowser() {
		killDriver();
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
		Assert.assertEquals("Cadastrado!", page.obterResultadoCadastro());
		Assert.assertEquals("Luiz",page.obterNomeCadastrado());
		Assert.assertEquals("Eduardo", page.obterSobrenomeCadastrado());
		Assert.assertEquals("Masculino", page.obterSexoCadastrado());
		Assert.assertEquals("Pizza", page.obterComidaCadastrada());
		Assert.assertEquals("superior",  page.obterEscolaridadeCadastrada());
		Assert.assertEquals("Futebol", page.obterEsporteCadastrado());
		Assert.assertEquals("Devo aprender a fazer automatização com o Selenium WebDriver.", page.obterSugestaoCadastrada());
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
		Assert.assertEquals("Cadastrado!", page.obterResultadoCadastro());
		Assert.assertEquals("Luiz", page.obterNomeCadastrado());
		Assert.assertEquals("Eduardo", page.obterSobrenomeCadastrado());
		Assert.assertEquals("Masculino", page.obterSexoCadastrado());
		Assert.assertEquals("Carne Pizza", page.obterComidaCadastrada());
		Assert.assertEquals("superior", page.obterEscolaridadeCadastrada());
		Assert.assertEquals("Natacao Futebol", page.obterEsporteCadastrado());
		Assert.assertEquals("Devo aprender a fazer automatização com o Selenium WebDriver.", page.obterSugestaoCadastrada());
	}
}

