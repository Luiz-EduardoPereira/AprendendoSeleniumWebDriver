import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	private WebDriver driver;
	public DSL(WebDriver driver) {
		this.driver = driver;
	}
	public void escrever(By by, String texto) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(texto);
	}
	public void escrever(String id, String texto) {
		escrever(By.id(id), texto);
	}
	public String obterValorCampo(String id) {
		return driver.findElement(By.id(id)).getAttribute("value");
	}
	public String obterTexto(By by) {
		return driver.findElement(by).getText();
	}
	public String obterTexto(String id) {
		return driver.findElement(By.id(id)).getText();
	}
	public boolean obterComecoTextoBoolean(String id, String texto) {
		return driver.findElement(By.id(id)).getText().startsWith(texto);
	}
	public void limparText(String id) {
		driver.findElement(By.id(id)).clear();
	}
	public boolean checarMarcacao(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}
	public void selecionarCombo(String id, String valor) {
		WebElement elemento = driver.findElement(By.id(id));
		Select combo = new Select(elemento);
		combo.selectByVisibleText(valor);
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
	public String obterValorCombo(String id) {
		WebElement elemento = driver.findElement(By.id(id));
		Select combo = new Select(elemento);
		return combo.getFirstSelectedOption().getText();
	}
	public List<String> obterValoresCombo(String id) {
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> valores = new ArrayList<String>();
		for(WebElement opcao: allSelectedOptions) {
			valores.add(opcao.getText());
		}
		return valores;
	}
	public int obterQuantidadeCombo(String id){
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getAllSelectedOptions();
		return options.size();
	}
	public int obterQuantidadeOpcoesCombo(String id){
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}
		public void clicar(By by) {
		driver.findElement(by).click();
	}
	public String alertaObterTexto() {
		Alert alerta = driver.switchTo().alert();
		return alerta.getText();
	}
	public String alertaObterTextoAceitar() {
		Alert alerta = driver.switchTo().alert();
		String valor = alerta.getText();
		alerta.accept();
		return valor;
	}
	public String alertaObterTextoNegar() {
		Alert alerta = driver.switchTo().alert();
		String valor = alerta.getText();
		alerta.dismiss();
		return valor;
	}
	public void alertaEscrever(String valor) {
		Alert alerta = driver.switchTo().alert();
		alerta.sendKeys(valor);
	}
	public void alertaAceitar() {
		Alert alerta = driver.switchTo().alert();
		alerta.accept();
	}
	public void alertaNegar() {
		Alert alerta = driver.switchTo().alert();
		alerta.dismiss();
	}
	public void entrarFrame(String id) {
		driver.switchTo().frame(id);
	}
	public void sairFrame() {
		driver.switchTo().defaultContent();
	}
	public void trocarJanela(String id) {
		driver.switchTo().window(id);
	}
}
	
