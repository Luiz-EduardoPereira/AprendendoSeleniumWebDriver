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
}
