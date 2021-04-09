package br.ce.luiz.page;
import org.openqa.selenium.By;

import br.ce.luiz.core.BasePage;

public class CampoTreinamentoPage extends BasePage{
	public void inserirNome(String nome) {
		dsl.escrever("elementosForm:nome", nome);
	}
	public void inserirSobrenome(String sobrenome) {
		dsl.escrever("elementosForm:sobrenome", sobrenome);
	}
	public void cadastrar() {
		dsl.clicar(By.id("elementosForm:cadastrar"));
	}
	public void setSexoMasculino() {
		dsl.clicar(By.id("elementosForm:sexo:0"));
	}
	public void setSexoFeminino() {
		dsl.clicar(By.id("elementosForm:sexo:1"));
	}
	public void setComidaPizza() {
		dsl.clicar(By.id("elementosForm:comidaFavorita:2"));
	}
	public void setComidaCarne() {
		dsl.clicar(By.id("elementosForm:comidaFavorita:0"));
	}
	public void setComidaVegetariano() {
		dsl.clicar(By.id("elementosForm:comidaFavorita:3"));
	}
	public void setEscolaridade(String valor) {
		dsl.selecionarCombo("elementosForm:escolaridade", valor);
	}
	public void setEsporte(String... valores) {
		for(String valor: valores)
		dsl.selecionarCombo("elementosForm:esportes", valor);
	}
	public void desmarcarEsporte(String valor) {
		dsl.desmarcarCombo("elementosForm:esportes", valor);
	}
	public void inserirSugestao(String sugestao) {
		dsl.escrever("elementosForm:sugestoes", sugestao);
	}
	public String obterResultadoCadastro() {
		return dsl.obterTexto(By.xpath("//*[@id='resultado']/span"));
	}
	public String obterNomeCadastrado() {
		return dsl.obterTexto(By.xpath("//*[@id='descNome']/span"));
	}
	public String obterSobrenomeCadastrado() {
		return dsl.obterTexto(By.xpath("//*[@id='descSobrenome']/span"));
	}
	public String obterSexoCadastrado() {
		return dsl.obterTexto(By.xpath("//*[@id='descSexo']/span"));
	}
	public String obterComidaCadastrada() {
		return dsl.obterTexto(By.xpath("//*[@id='descComida']/span"));
	}
	public String obterEscolaridadeCadastrada() {
		return dsl.obterTexto(By.xpath("//*[@id='descEscolaridade']/span"));
	}
	public String obterEsporteCadastrado() {
		return dsl.obterTexto(By.xpath("//*[@id='descEsportes']/span"));
	}
	public String obterSugestaoCadastrada() {
		 return dsl.obterTexto(By.xpath("//*[@id='descSugestoes']/span"));
	}
	public String obterTextoAlertEAceitar() {
		return dsl.alertaObterTextoAceitar();
	}
}

