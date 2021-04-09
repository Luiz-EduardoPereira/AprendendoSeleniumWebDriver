import static br.ce.luiz.core.DriverFactory.getDriver;
import static br.ce.luiz.core.DriverFactory.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.ce.luiz.core.DSL;

public class CampoTreinamentoAlert{	

	private DSL dsl;
	@Before
	public void inicializar() {
		getDriver().get("file:///" + System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	@After
	public void fecharBrowser() {
		killDriver();
	}
	@Test
	public void usarAlertSimples() {
		dsl.clicar(By.id("alert"));
		Assert.assertEquals("Alert Simples", dsl.alertaObterTextoAceitar());
		dsl.escrever("elementosForm:nome", "Alert Simples");
	}
	@Test
	public void usarAlertConfirm() {
		dsl.clicar(By.id("confirm"));
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoAceitar());
		Assert.assertEquals("Confirmado", dsl.alertaObterTextoAceitar());
	}
	@Test
	public void usarAlertCancelar() {
		dsl.clicar(By.id("confirm"));
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoNegar());
		Assert.assertEquals("Negado", dsl.alertaObterTextoAceitar());
	}
	@Test
	public void usarAlertPromptConfirmando() {
		dsl.clicar(By.id("prompt"));
		String numero = "123";
		dsl.alertaEscrever(numero);
		dsl.alertaAceitar();
		Assert.assertEquals("Era "+numero+"?", dsl.alertaObterTextoAceitar());
		Assert.assertEquals(":D", dsl.alertaObterTextoAceitar());
		}
	@Test
	public void usarAlertPromptNaoConfirmando() {
		dsl.clicar(By.id("prompt"));
		String numero = "123";
		dsl.alertaEscrever(numero);
		dsl.alertaAceitar();
		Assert.assertEquals("Era "+numero+"?", dsl.alertaObterTextoNegar());
		Assert.assertEquals(":(", dsl.alertaObterTextoAceitar());
	}
}
