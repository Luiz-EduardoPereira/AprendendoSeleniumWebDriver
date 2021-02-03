import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

public class CampoTreinamentoAlert extends CampoTreinamento{
	@Test
	public void usarAlertSimples() {
		inicializar();
		driver.findElement(By.id("alert")).click();
		Alert alerta = driver.switchTo().alert();
		String pegarTextAlert = alerta.getText();
		Assert.assertEquals("Alert Simples", pegarTextAlert);
		alerta.accept();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(pegarTextAlert);
	}
	@Test
	public void usarAlertConfirm() {
		inicializar();
		driver.findElement(By.id("confirm")).click();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alerta.getText());
		alerta.accept();
		Assert.assertEquals("Confirmado", alerta.getText());
		alerta.accept();
	}
	@Test
	public void usarAlertCancelar() {
		inicializar();
		driver.findElement(By.id("confirm")).click();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alerta.getText());
		alerta.dismiss();
		Assert.assertEquals("Negado", alerta.getText());
		alerta.accept();
	}
	@Test
	public void usarAlertPromptConfirmando() {
		inicializar();
		driver.findElement(By.id("prompt")).click();
		Alert alerta = driver.switchTo().alert();
		String numero = "123";
		alerta.sendKeys(numero);
		alerta.accept();
		Assert.assertEquals("Era "+numero+"?", alerta.getText());
		alerta.accept();
		Assert.assertEquals(":D", alerta.getText());
		alerta.accept();
		}
	@Test
	public void usarAlertPromptNaoConfirmando() {
		inicializar();
		driver.findElement(By.id("prompt")).click();
		Alert alerta = driver.switchTo().alert();
		String numero = "123";
		alerta.sendKeys(numero);
		alerta.accept();
		Assert.assertEquals("Era "+numero+"?", alerta.getText());
		alerta.dismiss();
		Assert.assertEquals(":(", alerta.getText());
		alerta.accept();
	}
}
