package br.ce.luiz.test;
import static br.ce.luiz.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.luiz.core.BaseTest;
import br.ce.luiz.page.CampoTreinamentoPage;

public class CampoTreinamentoRegraNegocio extends BaseTest {
	private CampoTreinamentoPage page;
	@Before
	public void inicializar() {
		getDriver().get("file:///" + System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage();
	}
	@Test
	public void regraNegocioNome() {
		page.cadastrar();
		Assert.assertEquals("Nome eh obrigatorio", page.obterTextoAlertEAceitar());
		page.inserirNome("Usxowu");
	}
	@Test
	public void regraNegocioSobrenome() {
		page.inserirNome("Usxowu");
		page.cadastrar();
		Assert.assertEquals("Sobrenome eh obrigatorio", page.obterTextoAlertEAceitar());
		page.inserirSobrenome("Laostmodea");
	}
	@Test
	public void regraNegocioSexo() {
		page.inserirNome("Usxowu");
		page.inserirSobrenome("Laostmodea");
		page.cadastrar();
		Assert.assertEquals("Sexo eh obrigatorio", page.obterTextoAlertEAceitar());
		page.setSexoMasculino();
	}
	@Test
	public void regraNegocioComida() {
		page.inserirNome("Usxowu");
		page.inserirSobrenome("Laostmodea");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setComidaVegetariano();
		page.cadastrar();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", page.obterTextoAlertEAceitar());
		page.setComidaCarne();
	}
	public void regraNegocioEsporte() {
		page.inserirNome("Usxowu");
		page.inserirSobrenome("Laostmodea");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setEsporte("Natacao", "O que eh esporte?");
		page.cadastrar();
		Assert.assertEquals("Voce faz esporte ou nao?", page.obterTextoAlertEAceitar());
		page.desmarcarEsporte("O que eh esporte?");
		page.cadastrar();
	}
}
