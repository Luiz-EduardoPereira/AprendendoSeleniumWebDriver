import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class CampoTreinamentoRegraNegocio {
	public static WebDriver driver;
	public void inicialiazar() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
	}
	public void btnCadastrar() {
		driver.findElement(By.id("elementosForm:cadastrar")).click();
	}
	public void regraNegocioNome() {
		inicialiazar();
		btnCadastrar();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alerta.getText());
		alerta.accept();
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Usxowu");
	}
	public void regraNegocioSobrenome() {
		btnCadastrar();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alerta.getText());
		alerta.accept();
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Laostmodea");
	}
	public void regraNegocioSexo() {
		btnCadastrar();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alerta.getText());
		alerta.accept();
		driver.findElement(By.id("elementosForm:sexo:0")).click();
	}
	public void regraNegocioComida() {
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		btnCadastrar();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alerta.getText());
		alerta.accept();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
	}
	public void regraNegocioEsporte() {
		WebElement opcoes = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(opcoes);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("O que eh esporte?");
		btnCadastrar();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Voce faz esporte ou nao?", alerta.getText());
		alerta.accept();
		combo.deselectByVisibleText("O que eh esporte?");
		combo.selectByVisibleText("Natacao");
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
