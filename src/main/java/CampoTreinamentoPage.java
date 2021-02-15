import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {
	
	private DSL dsl;
	public CampoTreinamentoPage(WebDriver driver) {
		dsl = new DSL(driver);
	}
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
	public void setComidaPizza() {
		dsl.clicar(By.id("elementosForm:comidaFavorita:2"));
	}
	public void setComidaCarne() {
		dsl.clicar(By.id("elementosForm:comidaFavorita:0"));
	}
	public void setEscolaridade(String valor) {
		dsl.selecionarCombo("elementosForm:escolaridade", valor);
	}
	public void setEsporte(String... valores) {
		for(String valor: valores)
		dsl.selecionarCombo("elementosForm:esportes", valor);
	}
	public void inserirSugestao(String sugestao) {
		dsl.escrever("elementosForm:sugestoes", sugestao);
	}
	public String obterResultadoCadastro() {
		return dsl.obterTexto("resultado");
	}
	public String obterNomeCadastrado() {
		return dsl.obterTexto("descNome");
	}
	public String obterSobrenomeCadastrado() {
		return dsl.obterTexto("descSobrenome");
	}
	public String obterSexoCadastrado() {
		return dsl.obterTexto("descSexo");
	}
	public String obterComidaCadastrada() {
		return dsl.obterTexto("descComida");
	}
	public String obterEscolaridadeCadastrada() {
		return dsl.obterTexto("descEscolaridade");
	}
	public String obterEsporteCadastrado() {
		return dsl.obterTexto("descEsportes");
	}
	public String obterSugestaoCadastrada() {
		 return dsl.obterTexto("descSugestoes");
	}
	public String obterTextoAlertEAceitar() {
		return dsl.alertaObterTextoAceitar();
	}
}

