package br.ce.luiz.core;

import static br.ce.luiz.core.DriverFactory.*;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

	public void escrever(By by, String texto) {
		getDriver().findElement(by).clear();
		getDriver().findElement(by).sendKeys(texto);
	}
	public void escrever(String id, String texto) {
		escrever(By.id(id), texto);
	}
	public String obterValorCampo(String id) {
		return getDriver().findElement(By.id(id)).getAttribute("value");
	}
	public String obterTexto(By by) {
		return getDriver().findElement(by).getText();
	}
	public String obterTexto(String id) {
		return getDriver().findElement(By.id(id)).getText();
	}
	public void limparText(String id) {
		getDriver().findElement(By.id(id)).clear();
	}
	public boolean checarMarcacao(By by) {
		return getDriver().findElement(by).isSelected();
	}
	public boolean checarMarcacao(String id) {
		return getDriver().findElement(By.id(id)).isSelected();
	}
	public void selecionarCombo(By by, String opcao, String valor) {
		WebElement elemento = getDriver().findElement(by);
		Select combo = new Select(elemento);
		elemento.findElement(By.xpath("./option[.="+opcao+""));
		combo.selectByVisibleText(valor);
	}
	public void selecionarCombo(String id, String valor) {
		WebElement elemento = getDriver().findElement(By.id(id));
		Select combo = new Select(elemento);
		combo.selectByVisibleText(valor);
	}
	public void selecionarComboPrimeFaces(String radical, String valor) {
		clicar(By.xpath("//select[@id='"+radical+"_input']/../..//span"));
		clicar(By.xpath("//div[@id='"+radical+"_panel']//li[.='"+valor+"']"));
	}
	public void desmarcarCombo(String id, String valor) {
		WebElement elemento = getDriver().findElement(By.id(id));
		Select combo = new Select(elemento);
		combo.deselectByVisibleText(valor);
	}
	public void desmarcarTodosCombo(String id) {
		WebElement elemento = getDriver().findElement(By.id(id));
		Select combo = new Select(elemento);
		combo.deselectAll();
	}
	public String obterValorCombo(String id) {
		WebElement elemento = getDriver().findElement(By.id(id));
		Select combo = new Select(elemento);
		return combo.getFirstSelectedOption().getText();
	}
	public List<String> obterValoresCombo(String id) {
		WebElement element = getDriver().findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> valores = new ArrayList<String>();
		for(WebElement opcao: allSelectedOptions) {
			valores.add(opcao.getText());
		}
		return valores;
	}
	public int obterQuantidadeCombo(String id){
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getAllSelectedOptions();
		return options.size();
	}
	public int obterQuantidadeOpcoesCombo(String id){
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}
		public void clicar(By by) {
		getDriver().findElement(by).click();
	}
	public String alertaObterTexto() {
		Alert alerta = getDriver().switchTo().alert();
		return alerta.getText();
	}
	public String alertaObterTextoAceitar() {
		Alert alerta = getDriver().switchTo().alert();
		String valor = alerta.getText();
		alerta.accept();
		return valor;
	}
	public String alertaObterTextoNegar() {
		Alert alerta = getDriver().switchTo().alert();
		String valor = alerta.getText();
		alerta.dismiss();
		return valor;
	}
	public void alertaEscrever(String valor) {
		Alert alerta = getDriver().switchTo().alert();
		alerta.sendKeys(valor);
	}
	public void alertaAceitar() {
		Alert alerta = getDriver().switchTo().alert();
		alerta.accept();
	}
	public void alertaNegar() {
		Alert alerta = getDriver().switchTo().alert();
		alerta.dismiss();
	}
	public void entrarFrame(String id) {
		getDriver().switchTo().frame(id);
	}
	public void sairFrame() {
		getDriver().switchTo().defaultContent();
	}
	public void trocarJanela(String id) {
		getDriver().switchTo().window(id);
	}
	public Object executarJS(String comando, Object... parametros) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		return js.executeScript(comando, parametros);
	}
	public void clicarBtnTabela(String colunaBusca, String valor, String colunaBotao, String idTabela) {
		WebElement tabela = getDriver().findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));
		int idColuna = obterIndiceColuna(colunaBusca, tabela);	
		int idLinha = obterIndiceLinha(valor, tabela, idColuna);
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
		WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
		celula.findElement(By.xpath(".//input")).click();
	}
	protected int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
		int idLinha = -1;
		for (int i = 0; i < linhas.size(); i++) {
			if (linhas.get(i).getText().equals(valor)) {
				idLinha = i+1; 
				break;
			}
		}
		return idLinha;
	}
	protected int obterIndiceColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
		int idColuna = -1;
		for (int i = 0; i < colunas.size(); i++) {
			if (colunas.get(i).getText().equals(coluna)) {
				idColuna = i+1;
				break;
			}
		}
		return idColuna;
	}
}
	
