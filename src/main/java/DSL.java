import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	private WebDriver driver;
	public DSL(WebDriver driver) {
		this.driver = driver;
	}
	public void escreve(String id, String texto) {
		driver.findElement(By.id(id)).sendKeys(texto);
	}
	public String obterValorCampo(String id_campo) {
		return driver.findElement(By.id(id_campo)).getAttribute("value");
	}
	public void limparText(String id) {
		driver.findElement(By.id(id)).clear();
	}
	public void clicarRadio(String id) {
		driver.findElement(By.id(id)).click();
	}
	public boolean checarMarcacaoRadio(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}
	public void clicarCheckBox(String id) {
		driver.findElement(By.id(id)).click();
	}
	public boolean checarMarcacaoCheckBox(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}
	public void SelecionarComboBox(String id, String valor) {
		WebElement elemento = driver.findElement(By.id(id));
		Select combo = new Select(elemento);
		combo.selectByVisibleText(valor);
	}
	public String obterValorCombo(String id) {
		WebElement elemento = driver.findElement(By.id(id));
		Select combo = new Select(elemento);
		return combo.getFirstSelectedOption().getText();
	}
	public void desmarcarCombo(String id, String valor) {
		WebElement elemento = driver.findElement(By.id(id));
		Select combo = new Select(elemento);
		combo.deselectByVisibleText(valor);
	}
	public void desmarcarTodosCombo(String id) {
		WebElement elemento = driver.findElement(By.id(id));
		Select combo = new Select(elemento);
		combo.deselectAll();
	}
	public void clicarBtn(String id) {
		driver.findElement(By.id(id)).click();
	}
	public void clicarLinkText(String linkText) {
		driver.findElement(By.linkText(linkText)).click();;
	}
	public String obterTexto(By by) {
		return driver.findElement(by).getText();
	}
	public String obterTexto(String id) {
		return driver.findElement(By.id(id)).getText();
	}
}
	
